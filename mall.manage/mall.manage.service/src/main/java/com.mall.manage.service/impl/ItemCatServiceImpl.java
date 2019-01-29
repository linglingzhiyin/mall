package com.mall.manage.service.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mall.common.bean.ItemCatData;
import com.mall.common.bean.ItemCatResult;
import com.mall.common.service.RedisService;
import com.mall.manage.pojo.ItemCat;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.manage.service.ItemCatService;

@Service
public class ItemCatServiceImpl extends BaseServiceAbst<ItemCat> implements ItemCatService{
    
    @Autowired
    private RedisService redisService;
    
    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final String REDIS_KEY = "TAOTAO_MANAGE_ITEM_CAT_TREE_";
    private static final Integer REDIS_TIME = 60*60*24*30*3;

    @Override
    public ItemCatResult queryAllToTree() {
        ItemCatResult result = new ItemCatResult();
        //先从缓存中命中
        try {
            String cacheData = this.redisService.get(REDIS_KEY);
            //判断命中的缓存是否为空
            if (StringUtils.isNoneEmpty(cacheData)) {
                //命中，返回,将转化成json存储在redis中的数据读出来
                return MAPPER.readValue(cacheData, ItemCatResult.class);
                
            }
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
        
        // 全部查出
        List<ItemCat> cats = super.queryAll();

        // 转为map存储，key为父节点ID，value为数据集合
        Map<Long, List<ItemCat>> itemCatMap = new HashMap<Long, List<ItemCat>>();
        for (ItemCat itemCat : cats) {
            if (!itemCatMap.containsKey(itemCat.getParentId())) {
                itemCatMap.put(itemCat.getParentId(), new ArrayList<ItemCat>());
            }
            itemCatMap.get(itemCat.getParentId()).add(itemCat);
        }

        // 封装一级对象
        List<ItemCat> itemCatList1 = itemCatMap.get(0L);
        for (ItemCat itemCat : itemCatList1) {
            ItemCatData itemCatData = new ItemCatData();
            itemCatData.setUrl("/products/" + itemCat.getId() + ".html");
            itemCatData.setName("<a href='" + itemCatData.getUrl() + "'>" + itemCat.getName() + "</a>");
            result.getItemCats().add(itemCatData);
            if (!itemCat.getIsParent()) {
                continue;
            }

            // 封装二级对象
            List<ItemCat> itemCatList2 = itemCatMap.get(itemCat.getId());
            List<ItemCatData> itemCatData2 = new ArrayList<ItemCatData>();
            itemCatData.setItems(itemCatData2);
            for (ItemCat itemCat2 : itemCatList2) {
                ItemCatData id2 = new ItemCatData();
                id2.setName(itemCat2.getName());
                id2.setUrl("/products/" + itemCat2.getId() + ".html");
                itemCatData2.add(id2);
                if (itemCat2.getIsParent()) {
                    // 封装三级对象
                    List<ItemCat> itemCatList3 = itemCatMap.get(itemCat2.getId());
                    List<String> itemCatData3 = new ArrayList<String>();
                    id2.setItems(itemCatData3);
                    for (ItemCat itemCat3 : itemCatList3) {
                        itemCatData3.add("/products/" + itemCat3.getId() + ".html|" + itemCat3.getName());
                    }
                }
            }
            if (result.getItemCats().size() >= 14) {
                break;
            }
        }

        try {
            //在返回数据之前将数据写入缓存中
            //此处不使用jdk自带的序列化方式将结果介入，而是使用jackson的方法将结果转化成json数据保存
            this.redisService.set(REDIS_KEY, MAPPER.writeValueAsString(result),REDIS_TIME);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}

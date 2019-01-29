package com.mall.manage.service.impl;

import java.util.*;

import com.mall.common.bean.EasyUIResult;
import com.mall.common.service.ApiService;
import com.mall.manage.mapper.ItemMapper;
import com.mall.manage.pojo.Item;
import com.mall.manage.pojo.ItemDesc;
import com.mall.manage.pojo.ItemParamItem;
import com.mall.manage.service.ItemParamItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.manage.service.ItemDescService;
import com.mall.manage.service.ItemService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class ItemServiceImpl extends BaseServiceAbst<Item> implements ItemService {

    @Autowired
    private ItemDescService itemDescService;

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemParamItemService itemParamItemService;

    @Autowired
    private ApiService apiService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;


//    @Autowired
//    private Jedis jedis;

    //    @Autowired
//    private RabbitTemplate  rabbitTemplate;
//
    private static final ObjectMapper MAPPER = new ObjectMapper();

    @Value("${MALL_WEB_URL}")
    private String MALL_WEB_URL;

    /**
     * 保存商品，此处将两个事务合并成一个事物
     */
    @Override
    public Boolean saveItem(Item item, String desc, String itemParams) {
        item.setStatus(1);//初始上架状态为1：正在上架
        item.setId(null);//出于安全考虑强制设置id为null通过数据库自增长得到
        Integer count_1 = super.save(item);


        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        Integer count_2 = this.itemDescService.save(itemDesc);

        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setItemId(item.getId());
        itemParamItem.setParamData(itemParams);
        Integer count_3 = this.itemParamItemService.save(itemParamItem);

        return count_1.intValue() == 1 && count_2.intValue() == 1 && count_3.intValue() == 1 ? true : false;
    }

    @Override
    public EasyUIResult queryItemList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Example example = new Example(Item.class);
        //按照创建时间倒叙排序
        example.setOrderByClause("created DESC");
        List<Item> items = this.itemMapper.selectByExample(example);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        return new EasyUIResult(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public Boolean updateItem(Item item, String desc, String itemParams) {

        //强制设置不能修改的字段为null,再进行商品基本数据修改
        item.setStatus(null);
        item.setCreated(null);
        Integer count_1 = super.updateByIdSelective(item);

        //修改商品描述数据
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemDesc(desc);
        itemDesc.setItemId(item.getId());
        Integer count_2 = itemDescService.updateByIdSelective(itemDesc);

        //修改商品规格参数
        Integer count_3 = this.itemParamItemService.updateItemParamItem(item.getId(), itemParams);

//        此方法耦合度太高，所有用MQ代替
/*        try {
            //此处调用前端服务提供的接口，已达到通知其他系统该商品已经进行了更新操作，从而进行缓存数据的删除更新
            String url = MALL_WEB_URL + "/item/cache/" + item.getId() + ".html";
            this.apiService.doPost(url, null);
        } catch (Exception e) {
            e.printStackTrace();
        }*/

        this.sendItemMsg(item.getId(), "update");
        return count_1.intValue() == 1 && count_2.intValue() == 1 && count_3.intValue() == 1 ? true : false;
    }

    /**
     * 将商品变化发送到消息队列
     *
     * @param itemId
     * @param type
     */


    private void sendItemMsg(Long itemId, String type) {
        try {
            Map<String, Object> msg = new LinkedHashMap<>();
            msg.put("type", type);
            msg.put("itemId", itemId);
            msg.put("date", System.currentTimeMillis());//加入消息发送是的时间戳
            System.out.println("发布者 ");
            this.redisTemplate.convertAndSend(REDIS_ITEM+"."+type, MAPPER.writeValueAsString(msg));

//            jedis.publish(itemId+"`$`"+type,MAPPER.writeValueAsString(msg));
//            this.rabbitTemplate.convertAndSend("item."+type, MAPPER.writeValueAsString(msg));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

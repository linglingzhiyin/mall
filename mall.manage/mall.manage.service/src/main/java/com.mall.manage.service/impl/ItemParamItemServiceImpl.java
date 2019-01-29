package com.mall.manage.service.impl;

import java.util.Date;

import com.mall.manage.mapper.ItemParamItemMapper;
import com.mall.manage.pojo.ItemParamItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.abel533.entity.Example;
import com.mall.manage.service.ItemParamItemService;

@Service
public class ItemParamItemServiceImpl extends BaseServiceAbst<ItemParamItem> implements ItemParamItemService{

    @Autowired
    private ItemParamItemMapper itemParamItemMapper;
    
    @Override
    public Integer updateItemParamItem(Long itemId, String itemParams) {
        //更新数据
        ItemParamItem itemParamItem = new ItemParamItem();
        itemParamItem.setParamData(itemParams);
        itemParamItem.setUpdated(new Date());
        
        //更新条件
        Example example = new Example(ItemParamItem.class);
        example.createCriteria().andEqualTo("itemId", itemId);
        return this.itemParamItemMapper.updateByExampleSelective(itemParamItem, example);
    }

}

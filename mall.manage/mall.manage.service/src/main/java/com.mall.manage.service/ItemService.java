package com.mall.manage.service;

import com.mall.common.bean.EasyUIResult;
import com.mall.manage.pojo.Item;

public interface ItemService extends BaseService<Item>{

    /**
     * 将保存商品的两个事务合成一个事务
     * @param item
     * @param desc
     * @param itemParams 
     */
    public static final String REDIS_ITEM="redis_item";
    Boolean saveItem(Item item, String desc, String itemParams);

    EasyUIResult queryItemList(Integer pageNum, Integer pageSize);

    Boolean updateItem(Item item, String desc, String itemParams);
}

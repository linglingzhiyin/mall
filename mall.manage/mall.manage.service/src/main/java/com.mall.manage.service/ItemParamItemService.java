package com.mall.manage.service;

import com.mall.manage.pojo.ItemParamItem;

public interface ItemParamItemService extends BaseService<ItemParamItem>{

    Integer updateItemParamItem(Long itemId, String itemParams);

}

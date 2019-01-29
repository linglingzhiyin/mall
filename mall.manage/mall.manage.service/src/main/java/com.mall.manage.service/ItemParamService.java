package com.mall.manage.service;

import com.mall.common.bean.EasyUIResult;
import com.mall.manage.pojo.ItemParam;

public interface ItemParamService extends BaseService<ItemParam>{

    EasyUIResult queryItemParamList(Integer pageNum, Integer pageSize);
}

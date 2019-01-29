package com.mall.manage.service;

import com.mall.common.bean.EasyUIResult;
import com.mall.manage.pojo.Content;

public interface ContentService extends BaseService<Content>{

    EasyUIResult queryPageListByCategoryId(Long categoryId, Integer pageNum, Integer pageSize);

}

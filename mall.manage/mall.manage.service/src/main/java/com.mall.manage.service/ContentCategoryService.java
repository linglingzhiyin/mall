package com.mall.manage.service;

import com.mall.manage.pojo.ContentCategory;

public interface ContentCategoryService extends BaseService<ContentCategory>{

    /**
     * 添加ContentCategory的方法，将两个事务合并成一个事务
     * @param contentCategory
     * @return
     */
    Boolean saveContentCategory(ContentCategory contentCategory);

    /**
     * 删除节点以及节点下的所有节点
     * @param contentCategory
     */
    void deleteAll(ContentCategory contentCategory);

}

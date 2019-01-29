package com.mall.manage.mapper;

import java.util.List;

import com.github.abel533.mapper.Mapper;
import com.mall.manage.pojo.Content;

public interface ContentMapper extends Mapper<Content>{

    /**
     * 根据categoryId查询，并根据更新时间倒序排序
     * @param categoryId
     * @return
     */
    List<Content> queryListByCategoryId(Long categoryId);

}

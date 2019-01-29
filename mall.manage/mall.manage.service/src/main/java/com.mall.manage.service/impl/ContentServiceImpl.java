package com.mall.manage.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.bean.EasyUIResult;
import com.mall.manage.mapper.ContentMapper;
import com.mall.manage.pojo.Content;
import com.mall.manage.service.ContentService;

@Service
public class ContentServiceImpl extends BaseServiceAbst<Content> implements ContentService{

    @Autowired
    private ContentMapper contentMapper;
    
    @Override
    public EasyUIResult queryPageListByCategoryId(Long categoryId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Content> contents = this.contentMapper.queryListByCategoryId(categoryId);
        PageInfo<Content> pageInfo = new PageInfo<>(contents);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }

}

package com.mall.manage.service.impl;

import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mall.common.bean.EasyUIResult;
import com.mall.manage.mapper.ItemParamMapper;
import com.mall.manage.pojo.Item;
import com.mall.manage.pojo.ItemParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mall.manage.service.ItemParamService;

import java.util.List;

@Service
public class ItemParamServiceImpl extends BaseServiceAbst<ItemParam> implements ItemParamService{

    @Autowired
    private ItemParamMapper itemParamMapper;


    @Override
    public EasyUIResult queryItemParamList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        Example example = new Example(ItemParam.class);
        //按照创建时间倒叙排序
        example.setOrderByClause("created DESC");
        List<ItemParam> items = this.itemParamMapper.selectByExample(example);
        PageInfo<ItemParam> pageInfo = new PageInfo<ItemParam>(items);
        return new EasyUIResult(pageInfo.getTotal(),pageInfo.getList());
    }
}

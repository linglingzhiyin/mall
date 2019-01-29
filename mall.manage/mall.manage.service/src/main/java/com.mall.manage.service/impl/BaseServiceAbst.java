package com.mall.manage.service.impl;


import java.util.Date;
import java.util.List;

import com.mall.manage.pojo.BasePojo;
import com.mall.manage.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

public abstract class BaseServiceAbst<T extends BasePojo> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;//spring4以后的功能:泛型注入

    @Override
    public T queryById(Long id) {
        T entity = this.mapper.selectByPrimaryKey(id);
        return entity;
    }


    @Override
    public List<T> queryAll() {
        List<T> list = this.mapper.select(null);
        return list;
    }

    @Override
    public T queryOne(T record) {
        T entity = this.mapper.selectOne(record);
        return entity;
    }

    @Override
    public List<T> queryListByWhere(T record) {
        return mapper.select(record);
    }

    @Override
    public PageInfo<T> queryPageListByWhere(T record,Integer pageNum,Integer pageSize) {
        //设置分页参数
        PageHelper.startPage(pageNum, pageSize);
        
        //获得对象
        List<T> list = this.mapper.select(record);
        
        return new PageInfo<T>(list);
    }

    @Override
    public Integer save(T entity) {
        entity.setCreated(new Date());
        entity.setUpdated(entity.getCreated());
        return this.mapper.insert(entity);
    }

    @Override
    public Integer saveSelective(T entity) {
        entity.setCreated(new Date());
        entity.setUpdated(entity.getCreated());
        return this.mapper.insertSelective(entity);
    }
    
    @Override
    public Integer updateById(T entity) {
        entity.setUpdated(new Date());
        return this.mapper.updateByPrimaryKey(entity);
    }
    
    @Override
    public Integer updateByIdSelective(T entity) {
        entity.setCreated(null); //强制设置创建时间为null，永远不会被更新
        entity.setUpdated(new Date());
        return this.mapper.updateByPrimaryKeySelective(entity);
    }

    @Override
    public Integer deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteByIds(Class<T> clazz, List<Object> ids, String property) {
        Example example = new Example(clazz);
        //创建条件并使用in语句进行多条操作
        example.createCriteria().andIn(property, ids);
        return this.mapper.deleteByExample(example);
    }

    @Override
    public Integer deleteByWhere(T record) {
        return this.mapper.delete(record);
    }
    
}

package com.mall.manage.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.mall.manage.pojo.BasePojo;

public interface BaseService<T extends BasePojo> {

    /**
     * 获得当前对象的mapper,在父类中得不到的可以让子类来实现
     * @return
     */
//    public abstract Mapper<T> getMapper() ;
    
    /**
     * 需要实现的服务
     * 1、queryById              通过ID查询
     * 2、queryAll               查询所有数据
     * 3、queryOne               条件查询一个,如果查询出多个数据会抛出异常
     * 4、queryListByWhere       条件查询
     * 5、queryPageListByWhere   条件查询并分页
     * 6、save                   保存
     * 7、update                 修改
     * 8、deleteById             通过ID删除
     * 9、deleteByIds            通过一堆ID删除
     * 10、deleteByWhere         通过条件删除
     */
    
    public T queryById(Long id) ;
    public List<T> queryAll() ;
    public T queryOne(T record) ;
    public List<T> queryListByWhere(T record) ;
    public PageInfo<T> queryPageListByWhere(T record, Integer pageNum, Integer pageSize) ;
    public Integer save(T entity) ;
    /**
     * 选择不为null的字段作为插入
     * @param entity
     * @return
     */
    public Integer saveSelective(T entity) ;
    public Integer updateById(T entity) ;
    /**
     * 选择不为null的字段作为更新的字段来进行主键修改
     * @param entity
     * @return
     */
    public Integer updateByIdSelective(T entity) ;
    public Integer deleteById(Long id) ;
    public Integer deleteByIds(Class<T> clazz, List<Object> ids, String property) ;
    public Integer deleteByWhere(T record) ;
}

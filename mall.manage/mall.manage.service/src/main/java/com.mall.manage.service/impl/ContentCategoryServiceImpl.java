package com.mall.manage.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.mall.manage.pojo.ContentCategory;
import com.mall.manage.service.ContentCategoryService;

@Service
public class ContentCategoryServiceImpl extends BaseServiceAbst<ContentCategory> implements ContentCategoryService{

    
    
    @Override
    public Boolean saveContentCategory(ContentCategory contentCategory) {
        Integer count_1 = super.save(contentCategory);
        
        //判断父节点isParent字段是否为false
        ContentCategory parent = super.queryById(contentCategory.getParentId());
        if (!parent.getIsParent()) {
            parent.setIsParent(true);
        }
        Integer count_2 = super.updateByIdSelective(parent);
        return count_1 == 1 && count_2 == 1;
    }

    @Override
    public void deleteAll(ContentCategory contentCategory) {
        //得到该一个容纳所有节点id的容器list
        List<Object> ids = new ArrayList<>();
        ids.add(contentCategory.getId());
        
        //递归查找该节点下的所有子节点并装入容器
        this.findAllSubNode(ids,contentCategory.getId());
        
        super.deleteByIds(ContentCategory.class, ids, "id");
        
        //判断该节点是否还有兄弟节点，若果没有，修改父节点的isParent为false
        ContentCategory record = new ContentCategory();
        record.setParentId(contentCategory.getParentId());
        List<ContentCategory> list = super.queryListByWhere(record);
        if (null== list || list.isEmpty()) {
            ContentCategory parent = new ContentCategory();
            parent.setId(contentCategory.getParentId());
            parent.setIsParent(false);
            super.updateByIdSelective(parent);
        }
        
    }

    /**
     * 找出当前节点下的所有子节点
     * @param ids
     * @param pid
     */
    private void findAllSubNode(List<Object> ids, Long pid) {
        ContentCategory record = new ContentCategory();
        record.setParentId(pid);
        //查找当前节点下的子节点
        List<ContentCategory> list = super.queryListByWhere(record);
        for (ContentCategory contentCategory : list) {
            
            ids.add(contentCategory.getId());
            //判断该节点是够为父节点
            if (contentCategory.getIsParent()) {
                //开始递归
                findAllSubNode(ids, contentCategory.getId());
            }
        }
    }   

    
}

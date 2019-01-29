package com.mall.manage.service;

//import com.mall.common.bean.ItemCatResult;
import com.mall.common.bean.ItemCatResult;
import com.mall.manage.pojo.ItemCat;

public interface ItemCatService extends BaseService<ItemCat>{

    /**
     * 将商品类目全部查出并在内存中生成树形结构
     * @return
     */
    ItemCatResult queryAllToTree();

}

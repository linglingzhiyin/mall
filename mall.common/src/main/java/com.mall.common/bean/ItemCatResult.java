package com.mall.common.bean;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 商品类目结果集
 * @author Administrator
 *
 */
public class ItemCatResult {

    @JsonProperty("data")
    private List<ItemCatData> itemCats = new ArrayList<ItemCatData>();

    public List<ItemCatData> getItemCats() {
            return itemCats;
    }

    public void setItemCats(List<ItemCatData> itemCats) {
            this.itemCats = itemCats;
    }
}

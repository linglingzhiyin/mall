package com.mall.web.bean;

import org.apache.commons.lang3.StringUtils;

public class Item extends com.mall.manage.pojo.Item{

    public String[] getImages() {
        return StringUtils.split(super.getImage(),",");
    }
    
}

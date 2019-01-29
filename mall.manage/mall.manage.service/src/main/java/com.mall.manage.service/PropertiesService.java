package com.mall.manage.service;

public interface PropertiesService {
    
    /**
     * 获取图片存储在本地的绝对路径
     * @return
     */
    public String getIMAGE_BASE_URL() ;
    
    /**
     * 获取nginx分配的静态资源访问地址
     * @return
     */
    public String getREPOSITORY_PATH();
}

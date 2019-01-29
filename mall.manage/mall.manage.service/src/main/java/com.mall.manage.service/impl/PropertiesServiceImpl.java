package com.mall.manage.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mall.manage.service.PropertiesService;

/**
 * 读取配置文件字段的服务
 * @author Administrator
 *
 */
@Service
public class PropertiesServiceImpl implements PropertiesService{

    @Value("${REPOSITORY_PATH}")
    public String REPOSITORY_PATH; 
    
   @Value("${IMAGE_BASE_URL}")
    public String IMAGE_BASE_URL;

    @Override
    public String getIMAGE_BASE_URL() {
        return IMAGE_BASE_URL;
    }

    @Override
    public String getREPOSITORY_PATH() {
        return REPOSITORY_PATH;
    }
    
    
}

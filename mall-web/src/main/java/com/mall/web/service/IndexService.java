package com.mall.web.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


import com.mall.common.bean.EasyUIResult;
import com.mall.common.service.ApiService;
import com.mall.manage.pojo.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

@Service
public class IndexService {
    
    private ObjectMapper MAPPER = new ObjectMapper();
    
    @Autowired
    private ApiService apiService;

   /* *
     * 查询大广告服务
     * @return
     * @throws JsonProcessingException
     * @throws IOException
     * */

    public String queryIndexAd1(){
        try {
            String url = "http://127.0.0.1:9001/rest/api/content?categoryId=33&page=1&rows=6";
            //通过httpclient获得json数据
            String jsonData = this.apiService.doGet(url);
            if (null == jsonData) {
                return null;
            }
            //解析json数据
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            
            List<Map<String, Object>> result = new ArrayList<>();
            for (JsonNode row : rows) {
                Map<String , Object> map = new LinkedHashMap<>();
                map.put("srcB", row.get("pic").asText());
                map.put("height", 240);
                map.put("alt", row.get("title").asText());
                map.put("width", 670);
                map.put("src", row.get("pic").asText());
                map.put("widthB", 550);
                map.put("href", row.get("url").asText()+".html");
                map.put("heightB", 240);
                result.add(map);
            }
            return MAPPER.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return null;
    }

   /* *
     * 查询右上角小广告/此处使用和查询大广告不同的方法，不同在于此处使用EasyUIResult
     * @return
     */

    public String queryIndexAd2() {
        try {
            String url = "http://127.0.0.1:9001/rest/api/content?categoryId=34&page=1&rows=1";
            //通过httpclient获得json数据
            String jsonData = this.apiService.doGet(url);
            if (null == jsonData) {
                return null;
            }
            //解析json数据
//            JsonNode jsonNode = MAPPER.readTree(jsonData);
//            ArrayNode rows = (ArrayNode) jsonNode.get("rows");
            EasyUIResult easyUIResult = EasyUIResult.formatToList(jsonData, Content.class);
            @SuppressWarnings("unchecked")
            List<Content> contents = (List<Content>) easyUIResult.getRows();
            
            List<Map<String, Object>> result = new ArrayList<>();
            for (Content content : contents) {
                Map<String , Object> map = new LinkedHashMap<>();
                map.put("width", 310);
                map.put("height", 70);
                map.put("src", content.getPic());
                map.put("href", content.getUrl()+".html");
                map.put("alt", content.getTitle());
                map.put("widthB", 210);
                map.put("heightB", 70);
                map.put("srcB", content.getPic());
                result.add(map);
            }
            return MAPPER.writeValueAsString(result);
        } catch (Exception e) {
            e.printStackTrace();
            
        } 
        return null;
    }
}

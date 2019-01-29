package com.mall.search.service;

import java.util.List;
import java.util.Map;

import com.mall.search.bean.Item;
import com.mall.search.bean.SearchResult;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSearchService {


    @Autowired
    private HttpSolrServer httpSolrServer;
    
    
    public SearchResult search(String keyWords, Integer page, Integer rows) {
        //构造搜索条件
        SolrQuery solrQuery = new SolrQuery();
        //在条件中加入关键字
        solrQuery.setQuery("title:"+keyWords+" AND status:1");
        //设置分页，设置开始条数,和一页显示的条数
        solrQuery.setStart((Math.max(page, 1)-1)*rows);
        solrQuery.setRows(rows);
        //是否需要高亮
        boolean isHighlighting = !StringUtils.equals("*", keyWords)&&StringUtils.isNotEmpty(keyWords);
        if (isHighlighting) {
            //设置高亮，开启高亮组件，字段，前缀和后缀
            solrQuery.setHighlight(true);
            solrQuery.addHighlightField("title");
            solrQuery.setHighlightSimplePre("<em>");
            solrQuery.setHighlightSimplePost("</em>");
        }
        try {
            //执行查询
            QueryResponse queryResponse = this.httpSolrServer.query(solrQuery);
            List<Item> list = queryResponse.getBeans(Item.class);
            //将高亮的标题数据写回到数据对象中
            if (isHighlighting) {
                Map<String, Map<String, List<String>>> map = queryResponse.getHighlighting();
                for (Map.Entry<String, Map<String, List<String>>> highlighting : map.entrySet()) {
                    for (Item item : list) {
                        if (!highlighting.getKey().equals(item.getId().toString())) {
                            continue;
                        }
                        item.setTitle(StringUtils.join(highlighting.getValue().get("title"),""));
                        break;
                    }
                }
            }
            return new SearchResult(queryResponse.getResults().getNumFound(),list);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}

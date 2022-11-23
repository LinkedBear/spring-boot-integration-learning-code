package com.linkedbear.boot.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.linkedbear.boot.elasticsearch.entity.GraphicsCard;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.beans.BeanMap;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.util.Map;

@SpringBootTest
public class ElasticSearchClientTest {
    
    @Value("${spring.elasticsearch.rest.uris}")
    private String esHost;
    
    @Test
    public void test1() throws Exception {
        RestClientBuilder builder = RestClient.builder(HttpHost.create(esHost));
        RestHighLevelClient client = new RestHighLevelClient(builder);
        System.out.println(client);
        client.close();
    }
    
    @Autowired
    private RestHighLevelClient restHighLevelClient;
    
    @Test
    public void testIndex() throws Exception {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setId(2);
        graphicsCard.setName("七彩虹（Colorful）战斧GeForce RTX 4090 豪华版");
        graphicsCard.setBrand("七彩虹");
        graphicsCard.setPrice(12999);
        String jsonString = new ObjectMapper().writeValueAsString(graphicsCard);
        IndexRequest request = new IndexRequest("gpu").id(graphicsCard.getId().toString())
                .source(jsonString, XContentType.JSON);
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }
    
    @Test
    public void testIndex3() throws Exception {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setId(3);
        graphicsCard.setName("华硕 ASUS TUF-GeForce RTX 3080 Ti-O12G-GAMING");
        graphicsCard.setBrand("华硕");
        graphicsCard.setPrice(7999);
        Map<String, Object> data = BeanMap.create(graphicsCard);
        IndexRequest request = new IndexRequest("gpu").id(graphicsCard.getId().toString()).source(data);
        restHighLevelClient.index(request, RequestOptions.DEFAULT);
    }
    
    @Test
    public void testSearch() throws Exception {
        SearchRequest request = new SearchRequest("gpu");
        
        // 添加查询条件
        SearchSourceBuilder builder = new SearchSourceBuilder();
//        builder.query(QueryBuilders.termQuery("name", "3080"));
//        builder.query(QueryBuilders.rangeQuery("price").lt(10000));
        builder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("name", "七彩虹"))
                .filter(QueryBuilders.rangeQuery("price").gte(10000)));
        request.source(builder);
        
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);
        for (SearchHit hit : response.getHits()) {
            System.out.println(hit.getSourceAsString());
        }
    }
    
    @Test
    public void testGet() throws Exception {
        GetRequest request = new GetRequest("gpu").id("3");
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        GraphicsCard graphicsCard = new ObjectMapper().readValue(response.getSourceAsString(), GraphicsCard.class);
        System.out.println(graphicsCard);
    }
    
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;
    
    @Test
    public void testTemplate() throws Exception {
        SearchHits<GraphicsCard> hits = elasticsearchRestTemplate
                .search(new CriteriaQuery(Criteria.where("name").contains("ROG")), GraphicsCard.class);
        for (org.springframework.data.elasticsearch.core.SearchHit<GraphicsCard> hit : hits) {
            System.out.println(hit.getContent());
        }
    
        hits = elasticsearchRestTemplate.search(new NativeSearchQuery(QueryBuilders.termQuery("name", "ROG")), GraphicsCard.class);
        for (org.springframework.data.elasticsearch.core.SearchHit<GraphicsCard> hit : hits) {
            System.out.println(hit.getContent());
        }
    }
}

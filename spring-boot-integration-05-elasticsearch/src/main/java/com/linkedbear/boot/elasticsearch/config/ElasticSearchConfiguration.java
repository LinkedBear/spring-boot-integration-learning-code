package com.linkedbear.boot.elasticsearch.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration(proxyBeanMethods = false)
public class ElasticSearchConfiguration {
    
    @Value("${spring.elasticsearch.rest.uris}")
    private String esHost;
    
    @Bean(destroyMethod = "close")
    public RestHighLevelClient restHighLevelClient() {
        RestClientBuilder builder = RestClient.builder(HttpHost.create(esHost));
        return new RestHighLevelClient(builder);
    }
}

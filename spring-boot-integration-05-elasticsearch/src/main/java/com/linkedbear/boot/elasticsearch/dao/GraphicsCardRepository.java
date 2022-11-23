package com.linkedbear.boot.elasticsearch.dao;

import com.linkedbear.boot.elasticsearch.entity.GraphicsCard;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GraphicsCardRepository extends ElasticsearchRepository<GraphicsCard, Integer> {
    
}

package com.linkedbear.boot.elasticsearch.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Document(indexName = "gpu")
public class GraphicsCard {
    
    @Id
    private Integer id;
    
    private String name;
    
    private String brand;
    
    private Integer price;
}
package com.linkedbear.boot.elasticsearch;

import com.linkedbear.boot.elasticsearch.dao.GraphicsCardRepository;
import com.linkedbear.boot.elasticsearch.entity.GraphicsCard;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataElasticSearchTest {
    
    @Autowired
    private GraphicsCardRepository graphicsCardRepository;
    
    @Test
    public void test1() throws Exception {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setId(1);
        graphicsCard.setName("ROG 玩家国度-STRIX-RTX4090-O24G-GAMING");
        graphicsCard.setBrand("ROG");
        graphicsCard.setPrice(15999);
        graphicsCardRepository.save(graphicsCard);
    }
    
    @Test
    public void test2() throws Exception {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setId(2);
        graphicsCard.setName("七彩虹（Colorful）战斧GeForce RTX 4090 豪华版");
        graphicsCard.setBrand("七彩虹");
        graphicsCard.setPrice(12999);
        graphicsCardRepository.save(graphicsCard);
    }
    
    @Test
    public void test3() throws Exception {
        GraphicsCard graphicsCard = new GraphicsCard();
        graphicsCard.setId(3);
        graphicsCard.setName("华硕 ASUS TUF-GeForce RTX 3080 Ti-O12G-GAMING");
        graphicsCard.setBrand("华硕");
        graphicsCard.setPrice(7999);
        graphicsCardRepository.save(graphicsCard);
    }
}

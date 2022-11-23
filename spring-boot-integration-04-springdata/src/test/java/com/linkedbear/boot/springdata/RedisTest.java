package com.linkedbear.boot.springdata;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.time.Duration;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

//@DataRedisTest
@SpringBootTest
public class RedisTest {
    
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    
    @Test
    public void test1() throws Exception {
        redisTemplate.opsForValue().set(111, 222);
        stringRedisTemplate.opsForList().rightPush("name", "aaaa");
        stringRedisTemplate.opsForList().rightPush("name", "bbb");
        stringRedisTemplate.opsForList().rightPush("name", "cc");
    }
    
    @Test
    public void test2() throws Exception {
        Object value = redisTemplate.opsForValue().get(111);
        System.out.println(value);
        List<String> names = stringRedisTemplate.opsForList().range("name", 0, 5);
        System.out.println(names);
    }
    
    @Test
    public void testValue() throws Exception {
        ValueOperations<Object, Object> valueOperations = redisTemplate.opsForValue();
        // 存入指定值
        valueOperations.set("abc", "def");
        valueOperations.setIfAbsent("qaz", 123);
        valueOperations.setIfAbsent("qaz", 456);
        // 存入指定值，并设置过期时间为1分钟（三种方式均可）
        valueOperations.set("qqq", 333, 1, TimeUnit.MINUTES);
        valueOperations.set("aaa", 444, TimeUnit.MINUTES.toMillis(1));
        valueOperations.set("zzz", 555, Duration.ofMinutes(1));
        // 取出指定值
        System.out.println("abc的值：" + valueOperations.get("abc"));
        System.out.println("qaz的值：" + valueOperations.get("qaz"));
        // 获取指定key的过期时间
        System.out.println("qqq的剩余有效时间：" + redisTemplate.getExpire("qqq"));
        // 删除指定值，多个值
        redisTemplate.delete("abc");
        redisTemplate.delete(Arrays.asList("qqq", "aaa", "zzz"));
        // 检查某个key是否存在
        System.out.println("qaz是否存在：" + redisTemplate.hasKey("qaz"));
        System.out.println("abc是否存在：" + redisTemplate.hasKey("abc"));
    }
    
    @Test
    public void testList() throws Exception {
        ListOperations<Object, Object> listOperations = redisTemplate.opsForList();
        // 向一个列表的左侧压入数据
        listOperations.leftPush("leftList", "aaa");
        // 一次性向左侧压入多个数据
        listOperations.leftPushAll("leftList", 123, 456, 789);
        // 获取一个列表的所有数据
        List<Object> leftList = listOperations.range("leftList", 0, 100);
        System.out.println("设置值后的leftList：" + leftList);
    
        // 向一个列表的右侧压入数据
        listOperations.rightPush("rightList", "qaz");
        // 一次性向右侧压入多个数据（List是允许重复的）
        listOperations.rightPushAll("rightList", 111, 222, 111, 333, 222, 333);
        List<Object> rightList = listOperations.range("rightList", 0, 100);
        System.out.println("设置值后的rightList：" + rightList);
        
        // 替换列表中的一个数据
        listOperations.set("leftList", 0, 999);
        System.out.println("替换值后的leftList：" + listOperations.range("leftList", 0, 100));
        
        // 根据索引获取数据
        System.out.println("leftList的第1个元素是：" + listOperations.index("leftList", 1));
        // 检查指定数据在列表的索引（报错说明redis版本低）
        System.out.println("456在leftList的位置：" + listOperations.indexOf("leftList", 456));
        
        // 向左弹出一个数据
        System.out.println("弹出leftList左侧的元素：" + listOperations.leftPop("leftList"));
        System.out.println("弹出leftList左侧的元素：" + listOperations.leftPop("leftList"));
        System.out.println("弹出leftList左侧的元素：" + listOperations.leftPop("leftList"));
        System.out.println("弹完数据后的leftList：" + listOperations.range("leftList", 0, 100));
        // 删除指定元素，中间的值决定了删除策略 0代表删除所有指定元素，> 0代表从左往右删除n个指定元素，< 0代表从右往左删除n个指定元素
        listOperations.remove("rightList", 0, 111);
        System.out.println("删除111后的rightList：" + listOperations.range("rightList", 0, 100));
        listOperations.remove("rightList", 1, 222);
        System.out.println("删除左起第1个222后的rightList：" + listOperations.range("rightList", 0, 100));
    }
    
    @Test
    public void testSet() throws Exception {
        SetOperations<Object, Object> setOperations = redisTemplate.opsForSet();
        // 添加元素（可多个）
        setOperations.add("names", "aaa", "bbb", "ccc");
        // 获取一个集合的所有元素
        Set<Object> names = setOperations.members("names");
        System.out.println("初次添加元素后的集合：" + names);
        // 从集合中随机取出一个/多个元素
        System.out.println("随机取出一个元素：" + setOperations.randomMember("names"));
        System.out.println("随机取出两个元素：" + setOperations.randomMembers("names", 2));
        // 移除一个指定的元素
        setOperations.remove("names", "aaa");
        System.out.println("删除aaa后的集合：" + setOperations.members("names"));
        // 随机移除一个元素
        setOperations.pop("names");
        System.out.println("随机删除一个元素后的集合：" + setOperations.members("names"));
        //删除整个集合
        redisTemplate.delete("names");
        System.out.println(setOperations.members("names"));
    }
    
    @Test
    public void testHash() throws Exception {
        HashOperations<Object, Object, Object> hashOperations = redisTemplate.opsForHash();
        // 向Hash中存数据
        hashOperations.put("scores", "bob", 80);
        hashOperations.put("scores", "lily", 90);
        hashOperations.put("scores", "tom", 60);
        // 取出指定值的所有数据
        Map<Object, Object> scores = hashOperations.entries("scores");
        System.out.println("当前scores的所有元素：" + scores);
        // 一次性把一个Map存入Hash
        Map<String, Object> data = new HashMap<>();
        data.put("john", 100);
        data.put("jack", 0);
        hashOperations.putAll("scores", data);
        System.out.println("当前scores的所有元素：" + hashOperations.entries("scores"));
        // 取出指定key指定hash的value
        System.out.println("lily的分数是：" + hashOperations.get("scores", "lily"));
        // 一次性取出多个hash的value
        System.out.println("任取三人的分数是：" + hashOperations.multiGet("scores", Arrays.asList("bob", "tom", "jack")));
        // 取出指定key的所有hash/values
        System.out.println("当前scores的所有人员：" + hashOperations.keys("scores"));
        System.out.println("当前scores的所有分数：" + hashOperations.values("scores"));
        // 移除指定key的指定hash（可以是多个）
        hashOperations.delete("scores", "lily");
        System.out.println("移除lily后scores的所有元素：" + hashOperations.entries("scores"));
    }
    
    @Test
    public void testZSet() throws Exception {
        ZSetOperations<Object, Object> zsetOperations = redisTemplate.opsForZSet();
        // 设置指定排序值的数据
        zsetOperations.add("pockets", "Pikachu", 5);
        zsetOperations.add("pockets", "Psyduck", 20);
        // 多个数据可以占用一个排序值
        zsetOperations.addIfAbsent("pockets", "Starmie", 30);
        zsetOperations.addIfAbsent("pockets", "Bulbasaur", 30);
        // 取出指定集合的所有元素
        Set<Object> pockets = zsetOperations.range("pockets", 0, 100);
        System.out.println("添加完毕后的宝可梦：" + pockets);
        // 调高/调低指定值得排序值
        zsetOperations.incrementScore("pockets", "Starmie", 5);
        zsetOperations.incrementScore("pockets", "Psyduck", -10);
        System.out.println("调整顺序后的宝可梦：" + zsetOperations.range("pockets", 0, 100));
        // 获取指定值的排序值和索引值(从0开始)
        System.out.println("可达鸭的当前排序值：" + zsetOperations.score("pockets", "Psyduck"));
        System.out.println("可达鸭的当前索引位置：" + zsetOperations.rank("pockets", "Psyduck"));
        // 获取指定排序值之间的元素
        System.out.println("0-20分之间的宝可梦：" + zsetOperations.rangeWithScores("pockets", 0, 20).stream()
                .map(ZSetOperations.TypedTuple::getValue).collect(Collectors.toList()));
        // 移除指定元素（remove，不再演示）
        // 移除指定索引值范围的元素（闭区间）
        zsetOperations.removeRange("pockets", 2, 3);
        System.out.println("删除指定位置的数据后的宝可梦：" + zsetOperations.range("pockets", 0, 100));
        // 移除指定分数区间的元素（闭区间）
        zsetOperations.removeRangeByScore("pockets", 5, 10);
        System.out.println("删除指定排序值后的宝可梦：" + zsetOperations.range("pockets", 0, 100));
        
        redisTemplate.delete("pockets");
    }
}

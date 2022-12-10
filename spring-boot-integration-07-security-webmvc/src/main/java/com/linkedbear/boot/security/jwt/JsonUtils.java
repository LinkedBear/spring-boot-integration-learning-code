package com.linkedbear.boot.security.jwt;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParseException;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class JsonUtils {
    
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    
    /**
     * json转Map
     * @param json
     * @return
     */
    public static Map<String, Object> parseObject(String json) {
        if (StringUtils.hasText(json)) {
            return parseObject(json, Map.class);
        }
        return new HashMap<>();
    }
    
    /**
     * json转指定类型
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T parseObject(String json, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            throw new JsonParseException(e);
        }
    }
    
    
    public static String toJson(Object obj) {
        if (obj == null) {
            return "";
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(e);
        }
    }
}

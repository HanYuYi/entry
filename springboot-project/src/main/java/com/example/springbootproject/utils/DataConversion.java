package com.example.springbootproject.utils;

import com.example.springbootproject.pojo.Blog;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;

/**
 * 序列化 和 反序列化 JSON
 */
public class DataConversion {
    private static ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将json字符串反序列化为对象
     * @param <T>
     * @param json
     * @param customType
     * @return
     */
    public static <T> T Deserialization(String json, Blog customType) {
        T result = null;
        try {
            result = (T) mapper.readValue(json, customType.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 将对象序列化为json字符串
     * @param customType
     * @param <E>
     * @return
     */
    public static <E> String serialize(E customType) {
        String result = null;
        try {
            result = mapper.writeValueAsString(customType);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }
}

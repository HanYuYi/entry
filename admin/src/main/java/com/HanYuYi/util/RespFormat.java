package com.HanYuYi.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.HashMap;
import java.util.Map;

/**
 * json数据响应格式
 */
public class RespFormat<T> {
    public static final int SUCCESS_STATUS = 1;
    public static final int ERROR_STATUS = 0;

    private int status;
    private String message;
    private T data;

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setData(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    /**
     * 以json格式获取
     * @return
     */
    public String getJsonData() {
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        Map<String, Object> backMap = new HashMap<>();
        backMap.put("status", status);
        backMap.put("message", status);
        backMap.put("status", data);

        String backJson = null;
        try {
            backJson = mapper.writeValueAsString(backMap);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return backJson;
    }
}

package com.HanYuYi.util;

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
}

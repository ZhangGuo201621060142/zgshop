package com.zg.zgshop.common.util;

/**
 * @Author: zg
 * @Date: 2019/3/12 20:08
 */
public class ResponseResult {

    //状态码
    private int status;

    //消息
    private String message;

    //返回数据
    private Object data;

    public ResponseResult() {
    }

    public ResponseResult(int status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功
     */
    public static ResponseResult success(Object data) {
        return new ResponseResult(1, "success", data);
    }

    public static ResponseResult success() {
        return new ResponseResult(1, "success", null);
    }

    /**
     * 失败
     * @param data
     * @return
     */
    public static ResponseResult error(Object data) {
        return new ResponseResult(0, "fail", data);
    }

    public static ResponseResult error(String msg) {
        return new ResponseResult(0, msg, null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}

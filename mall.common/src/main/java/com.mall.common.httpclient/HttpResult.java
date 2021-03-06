package com.mall.common.httpclient;

/**
 * http响应结果集
 * @author Administrator
 *
 */
public class HttpResult {
    private Integer code;
    private String body;
    public Integer getCode() {
        return code;
    }
    public void setCode(Integer code) {
        this.code = code;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public HttpResult(Integer code, String body) {
        super();
        this.code = code;
        this.body = body;
    }
    public HttpResult() {
    }
}

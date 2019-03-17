package com.awesome.mvp.exception;

/**
 * 服务器返回异常码
 */
public class ServerResponseException extends Exception{
    private int code;

    public ServerResponseException(String message,int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

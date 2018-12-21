package com.zgl.common.exception;

/**
 * @author zhugu
 * 2018/12/21 22:37
 */
public class ServiceException extends RuntimeException {
    public ServiceException(String msg) {
        super(msg);
    }
}

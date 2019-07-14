package com.zg.zgshop.common.exception;

/**
 * @Author: zg
 * @Date: 2019/3/12 19:49
 */
public class ProductTypeExistException extends Exception{

    public ProductTypeExistException() {
        super();
    }

    public ProductTypeExistException(String message) {
        super(message);
    }

    public ProductTypeExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeExistException(Throwable cause) {
        super(cause);
    }
}

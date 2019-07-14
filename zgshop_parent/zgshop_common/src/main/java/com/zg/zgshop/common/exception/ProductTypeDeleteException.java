package com.zg.zgshop.common.exception;

/**
 * @Author: zg
 * @Date: 2019/3/20 10:45
 */
public class ProductTypeDeleteException extends Exception {
    public ProductTypeDeleteException() {
        super();
    }

    public ProductTypeDeleteException(String message) {
        super(message);
    }

    public ProductTypeDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductTypeDeleteException(Throwable cause) {
        super(cause);
    }

    protected ProductTypeDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

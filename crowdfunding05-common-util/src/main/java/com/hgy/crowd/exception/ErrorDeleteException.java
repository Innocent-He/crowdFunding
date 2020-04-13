package com.hgy.crowd.exception;

/**
 * @Author He
 * @Date 2020/4/1 13:11
 * @Version 1.0
 */
public class ErrorDeleteException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ErrorDeleteException() {
        super();
    }

    public ErrorDeleteException(String message) {
        super(message);
    }

    public ErrorDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ErrorDeleteException(Throwable cause) {
        super(cause);
    }

    protected ErrorDeleteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

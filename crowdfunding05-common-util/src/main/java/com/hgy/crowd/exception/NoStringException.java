package com.hgy.crowd.exception;

/**
 * @Author He
 * @Date 2020/4/1 15:15
 * @Version 1.0
 */
public class NoStringException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public NoStringException() {
        super();
    }

    public NoStringException(String message) {
        super(message);
    }

    public NoStringException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoStringException(Throwable cause) {
        super(cause);
    }

    protected NoStringException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

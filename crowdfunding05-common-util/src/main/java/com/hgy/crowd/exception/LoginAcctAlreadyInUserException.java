package com.hgy.crowd.exception;

/**
 * @Author He
 * @Date 2020/4/1 14:41
 * @Version 1.0
 */
public class LoginAcctAlreadyInUserException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LoginAcctAlreadyInUserException() {
        super();
    }

    public LoginAcctAlreadyInUserException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUserException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUserException(Throwable cause) {
        super(cause);
    }

    protected LoginAcctAlreadyInUserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

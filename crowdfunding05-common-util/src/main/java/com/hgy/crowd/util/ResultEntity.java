package com.hgy.crowd.util;

/**
 * @Author He
 * @Date 2020/3/30 16:38
 * @Version 1.0
 */
public class ResultEntity <T>{
    public static final String SUCCESS="SUCCESS";
    public static final String FAILED="FAILED";

    private String message;
    private String result;
    private T data;
    public static  <E> ResultEntity<E> successWithoutData(){
        return new ResultEntity<E>(null,SUCCESS,null);
    }
    public static <E> ResultEntity<E> successWithData(E Data){
        return new ResultEntity<E>(null,SUCCESS,Data);
    }
    public static <E> ResultEntity<E> failed(String message){
        return new ResultEntity<E>(message,FAILED,null);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResultEntity(String message, String result, T data) {
        this.message = message;
        this.result = result;
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "message='" + message + '\'' +
                ", result='" + result + '\'' +
                ", data=" + data +
                '}';
    }
}

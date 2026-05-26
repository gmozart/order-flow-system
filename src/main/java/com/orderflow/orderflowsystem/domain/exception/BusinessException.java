package com.orderflow.orderflowsystem.domain.exception;

public class BusinessException extends RuntimeException{

    public BusinessException(String message){
        super(message);
    }

}

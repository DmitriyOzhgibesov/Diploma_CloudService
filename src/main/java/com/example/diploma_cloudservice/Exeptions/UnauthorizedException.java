package com.example.diploma_cloudservice.Exeptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String msg) {
        super(msg);
    }

}

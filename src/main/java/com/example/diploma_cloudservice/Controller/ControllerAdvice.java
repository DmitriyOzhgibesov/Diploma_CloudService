package com.example.diploma_cloudservice.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.diploma_cloudservice.Exeptions.FileException;
import com.example.diploma_cloudservice.Exeptions.InputDataException;
import com.example.diploma_cloudservice.Exeptions.UnauthorizedException;

@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InputDataException.class)
    public String HandlerInputData (InputDataException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public String HandlerUnauthorized (UnauthorizedException ex) {
        return ex.getMessage();
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileException.class)
    public String HandlerFile (FileException ex) {
        return ex.getMessage();
    }

}

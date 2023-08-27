package com.desafio.simbiose.crud.exception;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalErrorHandler {

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String nNotFoundHandler(HttpServletRequest request,
                                   HttpServletResponse response, Exception ex) {

        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();

        ErrorResponse error = ErrorResponse.builder()
                .mensagem(ex.getMessage())
                .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                .build();

        if(ex instanceof BusinessException) {

            error = ErrorResponse.builder()
                    .mensagem(ex.getMessage())
                    .status(String.valueOf(HttpStatus.NOT_FOUND.value()))
                    .build();
        }
        return gson.toJson(error);
    }
}

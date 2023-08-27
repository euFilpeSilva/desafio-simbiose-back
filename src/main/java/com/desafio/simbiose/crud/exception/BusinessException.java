package com.desafio.simbiose.crud.exception;

public class BusinessException extends RuntimeException {

    private String mensagem;

    public BusinessException(String msg) {
        super(msg);
        this.mensagem = msg;
    }

}

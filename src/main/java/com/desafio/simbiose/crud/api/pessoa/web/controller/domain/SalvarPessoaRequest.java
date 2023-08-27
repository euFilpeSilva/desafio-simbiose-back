package com.desafio.simbiose.crud.api.pessoa.web.controller.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class SalvarPessoaRequest {

    private String nome;

    private String email;

    private Date nascimento;
}

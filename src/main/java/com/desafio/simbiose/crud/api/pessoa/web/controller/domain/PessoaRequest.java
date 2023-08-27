package com.desafio.simbiose.crud.api.pessoa.web.controller.domain;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class PessoaRequest {

    private String nome;

    private String email;

    private Date nascimento;
}

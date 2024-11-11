package com.desafio.simbiose.crud.api.pessoa.web.controller.domain;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaResponse {

    private String id;

    private String nome;

    private String email;

    private Date nascimento;
}

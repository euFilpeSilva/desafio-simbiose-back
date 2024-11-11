package com.desafio.simbiose.crud.api.pessoa.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private String id;

    private String nome;

    private String email;

    private Date nascimento;
}

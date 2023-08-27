package com.desafio.simbiose.crud.api.pessoa.dto;

import lombok.*;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDto {

    private String nome;

    private String email;

    private Date nascimento;
}

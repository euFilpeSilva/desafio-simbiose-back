package com.desafio.simbiose.crud.api.pessoa.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document("pessoa")
@RequiredArgsConstructor
public class Pessoa {

    @Id
    private ObjectId id;

    private String nome;

    private String email;

    private Date nascimento;
}

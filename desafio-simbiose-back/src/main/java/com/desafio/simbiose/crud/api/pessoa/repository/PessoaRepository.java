package com.desafio.simbiose.crud.api.pessoa.repository;

import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends MongoRepository<Pessoa, String> {
}

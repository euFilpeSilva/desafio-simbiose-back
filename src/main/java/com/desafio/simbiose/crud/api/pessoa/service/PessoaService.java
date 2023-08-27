package com.desafio.simbiose.crud.api.pessoa.service;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.repository.PessoaRepository;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private final PessoaRepository repository;

    private final PessoaMapper mapper;

    public void salvaOuAtualizaPessoa(PessoaDto dto) {

        if (Optional.ofNullable(dto.getId()).isPresent()) {
            Pessoa pessoa = mapper.toDtoPessoa(dto);
            repository.save(pessoa);
        } else {
            repository.save(mapper.toDtoPessoa(dto));
        }

    }
}

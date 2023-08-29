package com.desafio.simbiose.crud.api.pessoa.service;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.repository.PessoaRepository;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import com.desafio.simbiose.crud.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public void excluirPessoa(final String id) {

        Optional<Pessoa> pessoaOptional;
        try {
            pessoaOptional = repository.findById(new ObjectId(id));
        } catch (Exception e) {
            pessoaOptional = Optional.empty();
        }

        if (pessoaOptional.isEmpty()) {
            throw new BusinessException("Registro não localizado no banco");
        }

        pessoaOptional.ifPresent(repository::delete);
    }

    public Page<Pessoa> listarPessoas(Pageable page) {

        return repository.findAll(page);
    }
}

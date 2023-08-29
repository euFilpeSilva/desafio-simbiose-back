package com.desafio.simbiose.crud.api.pessoa.service;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.repository.PessoaRepository;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import com.desafio.simbiose.crud.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PessoaService {

    private static final String NOT_FOUND = "Dados não encontrados na base de dados";
    private static final String NOT_DELETE = "Falha na execução da operação";
    private static final String NOT_SALV = "Erro ao salvar ou atualizar a pessoa.";

    private final PessoaRepository repository;

    private final PessoaMapper mapper;

    public void salvaOuAtualizaPessoa(PessoaDto dto) {
        try {
            if (Optional.ofNullable(dto.getId()).isPresent()) {
                Pessoa pessoa = mapper.toDtoPessoa(dto);
                repository.save(pessoa);
            } else {
                repository.save(mapper.toDtoPessoa(dto));
            }
        } catch (Exception e) {
            throw new BusinessException(NOT_SALV);
        }
    }


    public Page<PessoaDto> listarPessoas(Pageable page) {
        Page<Pessoa> pessoasPage = repository.findAll(page);

        if (pessoasPage.isEmpty()) {
            throw new BusinessException(NOT_FOUND);
        }

        return pessoasPage.map(mapper::toPessoaDto);
    }


    public PessoaDto buscarPorId(String id) {
        Optional<Pessoa> pessoaOptional = repository.findById(id);

        if (pessoaOptional.isPresent()) {
            Pessoa pessoa = pessoaOptional.get();
            return mapper.toPessoaDto(pessoa);
        } else {
            throw new BusinessException(NOT_FOUND);
        }
    }

    public void deletarPorId(String id) {
        try {
            repository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new BusinessException(NOT_DELETE);
        }
    }

}

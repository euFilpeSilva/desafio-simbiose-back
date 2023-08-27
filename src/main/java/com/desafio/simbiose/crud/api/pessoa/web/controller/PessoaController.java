package com.desafio.simbiose.crud.api.pessoa.web.controller;

import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.repository.PessoaRepository;
import com.desafio.simbiose.crud.api.pessoa.service.PessoaService;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.PessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoa")
@RequiredArgsConstructor
@Validated
public class PessoaController {

    private final PessoaService service;

    private final PessoaMapper mapper;

    @PostMapping
    public ResponseEntity<String> salvarPessoa(@RequestBody final PessoaRequest request) {
        service.adicionaPessoa(mapper.toDto(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
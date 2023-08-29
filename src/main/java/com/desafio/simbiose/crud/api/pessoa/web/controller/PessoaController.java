package com.desafio.simbiose.crud.api.pessoa.web.controller;

import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.service.PessoaService;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.AtualizarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.SalvarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/pessoa")
@RequiredArgsConstructor
@Validated
public class PessoaController {

    private final PessoaService service;

    private final PessoaMapper mapper;

    @PostMapping
    public ResponseEntity<String> salvarPessoa(@RequestBody final SalvarPessoaRequest request) {
        service.salvaOuAtualizaPessoa(mapper.toDto(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> atualizaPessoa(@RequestBody final AtualizarPessoaRequest request) {
        service.salvaOuAtualizaPessoa(mapper.toDto(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> excluirPessoa(@RequestBody final String id) {
        service.excluirPessoa(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public Page<Pessoa> listarPessoas(Pageable pagina) {

        return service.listarPessoas(pagina);
    }

}
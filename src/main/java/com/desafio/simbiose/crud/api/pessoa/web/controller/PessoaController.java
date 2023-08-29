package com.desafio.simbiose.crud.api.pessoa.web.controller;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.service.PessoaService;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.AtualizarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.PessoaResponse;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.SalvarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

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

    @GetMapping
    public Page<PessoaResponse> listarPessoas(Pageable pagina) {
        Page<PessoaDto> pessoasPage = service.listarPessoas(pagina);
        return pessoasPage.map(mapper::toPessoaResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaResponse> buscarPessoaPorId(@PathVariable String id) {
        PessoaDto pessoaDto = service.buscarPorId(id);

        if (nonNull(pessoaDto)) {
            PessoaResponse pessoaResponse = mapper.toPessoaResponse(pessoaDto);
            return ResponseEntity.ok(pessoaResponse);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoaPorId(@PathVariable String id) {
        service.deletarPorId(id);
        return ResponseEntity.noContent().build();
    }
}
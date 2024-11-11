package com.desafio.simbiose.crud.api.pessoa.web.controller.mapper;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.AtualizarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.PessoaResponse;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.SalvarPessoaRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    PessoaDto toDto(SalvarPessoaRequest request);

    Pessoa toDtoPessoa(PessoaDto dto);

    PessoaDto toDto(AtualizarPessoaRequest request);

    PessoaResponse toPessoaResponse(PessoaDto pessoa);

    PessoaDto toPessoaDto(Pessoa pessoa);
}

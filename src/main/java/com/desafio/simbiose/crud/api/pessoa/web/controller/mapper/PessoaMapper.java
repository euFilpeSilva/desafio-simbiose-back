package com.desafio.simbiose.crud.api.pessoa.web.controller.mapper;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.PessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.PessoaResponse;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    default Page<PessoaResponse> toResponse(Page<PessoaDto> dtoPage) {
        return Optional.ofNullable(dtoPage).map(entPage -> entPage.map(this::toResponse)).orElse(null);
    }

    PessoaResponse toResponse(PessoaDto dtos);

    List<PessoaResponse> toResponse(List<PessoaDto> dto);

    PessoaDto toDto(PessoaRequest request);

    Pessoa toDtoPessoa(PessoaDto dto);


}

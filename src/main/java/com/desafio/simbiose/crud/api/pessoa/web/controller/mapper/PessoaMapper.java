package com.desafio.simbiose.crud.api.pessoa.web.controller.mapper;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.AtualizarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.PessoaResponse;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.SalvarPessoaRequest;
import org.bson.types.ObjectId;
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

    PessoaDto toDto(SalvarPessoaRequest request);

    PessoaDto toDto(AtualizarPessoaRequest request);

    default Pessoa toDtoPessoa(PessoaDto dto) {
        Pessoa p = new Pessoa();

        Optional<String> idOptional = Optional.ofNullable(dto.getId());

        idOptional.ifPresent(id -> p.setId(new ObjectId(id)));

        p.setEmail(dto.getEmail());
        p.setNome(dto.getNome());
        p.setNascimento(dto.getNascimento());

        return p;
    }

    ObjectId map(String source);


}

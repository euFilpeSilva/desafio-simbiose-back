package api.pessoa.web.controller;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.service.PessoaService;
import com.desafio.simbiose.crud.api.pessoa.web.controller.PessoaController;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.AtualizarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.PessoaResponse;
import com.desafio.simbiose.crud.api.pessoa.web.controller.domain.SalvarPessoaRequest;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @InjectMocks
    private PessoaController pessoaController;

    @Mock
    private PessoaService service;

    @Mock
    private PessoaMapper mapper;

    @Test
    void testSalvarPessoa() {

        SalvarPessoaRequest request = new SalvarPessoaRequest();
        request.setNome("Nome de Teste");
        request.setEmail("teste@example.com");
        request.setNascimento(new Date());

        PessoaDto pessoaDto = new PessoaDto();
        when(mapper.toDto(request)).thenReturn(pessoaDto);

        doNothing().when(service).salvaOuAtualizaPessoa(any(PessoaDto.class));

        PessoaController controller = new PessoaController(service, mapper);

        ResponseEntity<String> response = controller.salvarPessoa(request);

        assertEquals(HttpStatus.OK, response.getStatusCode());

        verify(mapper, times(1)).toDto(request);
        verify(service, times(1)).salvaOuAtualizaPessoa(pessoaDto);
    }

    @Test
    void testAtualizaPessoa() {

        AtualizarPessoaRequest request = new AtualizarPessoaRequest();
        request.setId("5265gtg");
        request.setNome("Nome de Teste Atualizado");
        request.setEmail("atualizado@example.com");

        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId("5265gtg");
        pessoaDto.setNome("Att");
        pessoaDto.setEmail("atualizado3@example.com");

        when(mapper.toDto(any(AtualizarPessoaRequest.class))).thenReturn(pessoaDto);
        doNothing().when(service).salvaOuAtualizaPessoa(any(PessoaDto.class));

        ResponseEntity<String> responseEntity = pessoaController.atualizaPessoa(request);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testListarPessoas() {

        PessoaController pessoaController = new PessoaController(service, mapper);

        Pageable pageable = Pageable.unpaged();

        List<PessoaDto> pessoaDtos = new ArrayList<>();
        Page<PessoaDto> pessoaDtoPage = new PageImpl<>(pessoaDtos, pageable, pessoaDtos.size());

        when(service.listarPessoas(pageable)).thenReturn(pessoaDtoPage);

        Page<PessoaResponse> responsePage = pessoaController.listarPessoas(pageable);

        assertEquals(pessoaDtos.size(), responsePage.getTotalElements());
    }

    @Test
    void testBuscarPessoaPorId() {

        String id = "2";

        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setId("5265gtg");
        pessoaDto.setNome("Att");
        pessoaDto.setEmail("aleatorio@example.com");

        when(service.buscarPorId(id)).thenReturn(pessoaDto);

        PessoaResponse pessoaResponse = new PessoaResponse();
        pessoaResponse.setId("5265gtg");
        pessoaResponse.setNome("Nome fantasia");
        pessoaResponse.setEmail("aleatorio@example.com");

        when(mapper.toPessoaResponse(pessoaDto)).thenReturn(pessoaResponse);

        ResponseEntity<PessoaResponse> responseEntity = pessoaController.buscarPessoaPorId(id);

        assertEquals(pessoaResponse, responseEntity.getBody());
    }

    @Test
    void testDeletarPessoaPorId() {

        String id = "f48fe9f4";

        doNothing().when(service).deletarPorId(id);

        ResponseEntity<Void> responseEntity = pessoaController.deletarPessoaPorId(id);

        assertNotNull(responseEntity);
        assertEquals(204, responseEntity.getStatusCodeValue());
        verify(service, times(1)).deletarPorId(id);
    }
}



package api.pessoa.service;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.repository.PessoaRepository;
import com.desafio.simbiose.crud.api.pessoa.service.PessoaService;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import com.desafio.simbiose.crud.exception.BusinessException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.mongodb.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaService pessoaService;

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private PessoaMapper pessoaMapper;

    @Test
    void testSalvar() {
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Nome de Teste");
        pessoaDto.setEmail("teste@example.com");
        pessoaDto.setNascimento(new Date());

        when(pessoaMapper.toDtoPessoa(any(PessoaDto.class))).thenReturn(new Pessoa());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(new Pessoa());

        pessoaService.salvaOuAtualizaPessoa(pessoaDto);

        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void testAtualizar() {

        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Novo teste");
        pessoaDto.setEmail("teste2@example.com");
        pessoaDto.setNascimento(new Date());

        pessoaDto.setId("10");

        when(pessoaMapper.toDtoPessoa(any(PessoaDto.class))).thenReturn(new Pessoa());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(new Pessoa());

        pessoaService.salvaOuAtualizaPessoa(pessoaDto);

        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }

    @Test
    void testListarPessoas() {

        Pageable pageable = Pageable.unpaged();

        List<Pessoa> pessoas = new ArrayList<>();
        Page<Pessoa> pessoaPage = new PageImpl<>(pessoas, pageable, pessoas.size());

        when(pessoaRepository.findAll(pageable)).thenReturn(pessoaPage);

        Page<PessoaDto> pessoaDtoPage = pessoaService.listarPessoas(pageable);

        assertEquals(pessoas.size(), pessoaDtoPage.getTotalElements());
    }

    @Test
    void testBuscarPorId_PessoaEncontrada() {
        // Arrange
        String id = "5";

        Pessoa pessoa = new Pessoa();
        pessoa.setId("2");
        pessoa.setNome("Novo teste");
        pessoa.setEmail("teste2@example.com");
        pessoa.setNascimento(new Date());

        when(pessoaRepository.findById(id)).thenReturn(Optional.of(pessoa));

        PessoaDto pessoaDto = new PessoaDto();
        when(pessoaMapper.toPessoaDto(pessoa)).thenReturn(pessoaDto);

        PessoaDto result = pessoaService.buscarPorId(id);

        assertNotNull(result);
    }

    @Test
    void testBuscarPorId_PessoaNaoEncontrada() {

        String id = "635f3190669cc07be7b57f50";

        when(pessoaRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(BusinessException.class, () -> pessoaService.buscarPorId(id));
    }

    @Test
    void testDeletarPessoaPorId() {
        String id = "f48fe9f4";

        pessoaService.deletarPorId(id);

        verify(pessoaRepository, times(1)).deleteById(id);
    }

}

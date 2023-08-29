package api.pessoa.service;

import com.desafio.simbiose.crud.api.pessoa.dto.PessoaDto;
import com.desafio.simbiose.crud.api.pessoa.entity.Pessoa;
import com.desafio.simbiose.crud.api.pessoa.repository.PessoaRepository;
import com.desafio.simbiose.crud.api.pessoa.service.PessoaService;
import com.desafio.simbiose.crud.api.pessoa.web.controller.mapper.PessoaMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

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
        // Arrange
        PessoaDto pessoaDto = new PessoaDto();
        pessoaDto.setNome("Novo teste");
        pessoaDto.setEmail("teste2@example.com");
        pessoaDto.setNascimento(new Date());

        pessoaDto.setId("10");

        when(pessoaMapper.toDtoPessoa(any(PessoaDto.class))).thenReturn(new Pessoa());
        when(pessoaRepository.save(any(Pessoa.class))).thenReturn(new Pessoa());

        // Act
        pessoaService.salvaOuAtualizaPessoa(pessoaDto);

        // Assert
        verify(pessoaRepository, times(1)).save(any(Pessoa.class));
    }
}

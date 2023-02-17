package com.jean.attornatus;

import com.jean.attornatus.Exceptions.EntidadeNaoEncontradaException;
import com.jean.attornatus.domain.Pessoa;
import com.jean.attornatus.repository.PessoaRepository;
import com.jean.attornatus.service.PessoaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveListarPessoas() {
        // Criação de pessoas para o teste
        List<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa(1L, "João da Silva", LocalDate.of(1990, 3, 15)));
        pessoas.add(new Pessoa(2L, "Maria Oliveira", LocalDate.of(1985, 8, 23)));
        pessoas.add(new Pessoa(3L, "José Santos", LocalDate.of(1995, 10, 1)));

        // Define o comportamento do mock repository
        when(pessoaRepository.findAll()).thenReturn(pessoas);

        // Chama o método a ser testado
        List<Pessoa> resultado = pessoaService.listarPessoas();

        // Verifica se o resultado retornado é igual à lista de pessoas criada
        assertThat(resultado).isEqualTo(pessoas);
    }

    // Outros testes podem ser adicionados aqui
}
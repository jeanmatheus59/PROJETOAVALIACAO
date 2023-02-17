package com.jean.attornatus;

import com.jean.attornatus.Exceptions.EntidadeNaoEncontradaException;
import com.jean.attornatus.domain.Endereco;
import com.jean.attornatus.domain.Pessoa;
import com.jean.attornatus.repository.EnderecoRepository;
import com.jean.attornatus.repository.PessoaRepository;
import com.jean.attornatus.service.EnderecoService;
import jdk.internal.org.objectweb.asm.util.CheckClassAdapter;
import org.assertj.core.api.AssertionsForClassTypes;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class EnderecoServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void deveCriarEndereco() {
        // Criação de uma pessoa e um endereço para o teste
        Pessoa pessoa = new Pessoa(1L, "João da Silva", LocalDate.of(1990, 3, 15));
        Endereco endereco = new Endereco(1L, "Rua dos Bobos", "12345-678", "10", "São Paulo", pessoa);

        // Define o comportamento do mock repository
        when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.of(pessoa));
        when(enderecoRepository.save(endereco)).thenReturn(endereco);

        // Chama o método a ser testado
        Endereco resultado = enderecoService.criarEndereco(endereco);

        // Verifica se o resultado retornado é igual ao endereço criado
        MatcherAssert.assertThat(resultado).isEqualTo(endereco);
        // Verifica se a pessoa relacionada ao endereço foi atualizada corretamente
        MatcherAssert.assertThat(pessoa.getEnderecos()).contains(endereco);
        // Verifica se o método save foi chamado corretamente no repository
        CheckClassAdapter.verify(enderecoRepository).save(endereco);
    }

    @Test
    public void deveLancarExcecaoSePessoaNaoExistirAoCriarEndereco() {
        // Criação de um endereço com uma pessoa inexistente para o teste
        Pessoa pessoa = new Pessoa(1L, "João da Silva", LocalDate.of(1990, 3, 15));
        Endereco endereco = new Endereco(1L, "Rua dos Bobos", "12345-678", "10", "São Paulo", pessoa);

        // Define o comportamento do mock repository
        when(pessoaRepository.findById(pessoa.getId())).thenReturn(Optional.empty());

        // Verifica se o método a ser testado lança a exceção correta
        AssertionsForClassTypes.assertThatThrownBy(() -> enderecoService.criarEndereco(endereco))
                .isInstanceOf(EntidadeNaoEncontradaException.class)
                .hasMessage("Pessoa não encontrada com o ID informado");
    }

    // Outros testes podem ser adicionados aquiiii
}

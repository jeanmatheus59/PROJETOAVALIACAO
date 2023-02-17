package com.jean.attornatus.service;

import com.jean.attornatus.Exceptions.ResourceNotFoundException;
import com.jean.attornatus.domain.Endereco;
import com.jean.attornatus.domain.Pessoa;
import com.jean.attornatus.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private PessoaService pessoaService;

    public Endereco criarEndereco(Long pessoaId, Endereco endereco) {
        Pessoa pessoa = pessoaService.consultarPessoa(pessoaId);
        pessoa.addEndereco(endereco);
        return enderecoRepository.save(endereco);
    }

    public List<Endereco> listarEnderecos(Long pessoaId) {
        return enderecoRepository.findByPessoaId(pessoaId);
    }

    public Endereco consultarEndereco(Long id) {
        return enderecoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
    }

    public Endereco editarEndereco(Long id, Long aLong, Endereco enderecoAtualizado) {
        Endereco endereco = consultarEndereco(id);
        endereco.setLogradouro(enderecoAtualizado.getLogradouro());
        endereco.setCep(enderecoAtualizado.getCep());
        endereco.setNumero(enderecoAtualizado.getNumero());
        endereco.setCidade(enderecoAtualizado.getCidade());
        endereco.setPrincipal(enderecoAtualizado.isPrincipal());
        return enderecoRepository.save(endereco);
    }

    public void excluirEndereco(Long id, Long aLong) {
        Endereco endereco = consultarEndereco(id);
        Pessoa pessoa = endereco.getPessoa();
        pessoa.removeEndereco(endereco);
        enderecoRepository.delete(endereco);
    }

    public Endereco definirEnderecoPrincipal(Long pessoaId, Long enderecoId) {
        Pessoa pessoa = pessoaService.consultarPessoa(pessoaId);
        Endereco enderecoPrincipalAtual = enderecoRepository.findByPessoaIdAndPrincipalTrue(pessoaId).orElse(null);
        if (enderecoPrincipalAtual != null) {
            enderecoPrincipalAtual.setPrincipal(false);
            enderecoRepository.save(enderecoPrincipalAtual);
        }
        Endereco enderecoPrincipalNovo = enderecoRepository.findById(enderecoId).orElseThrow(() -> new ResourceNotFoundException("Endereço não encontrado"));
        enderecoPrincipalNovo.setPrincipal(true);
        return enderecoRepository.save(enderecoPrincipalNovo);
    }
}

package com.jean.attornatus.service;

import com.jean.attornatus.Exceptions.ResourceNotFoundException;
import com.jean.attornatus.domain.Pessoa;
import com.jean.attornatus.repository.EnderecoRepository;
import com.jean.attornatus.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa criarPessoa(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa consultarPessoa(Long id) {
        return pessoaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pessoa não encontrada"));
    }

    public List<Pessoa> listarPessoas() {
        return pessoaRepository.findAll();
    }

    public Pessoa editarPessoa(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoa = consultarPessoa(id);
        pessoa.setNome(pessoaAtualizada.getNome());
        pessoa.setDataNascimento(pessoaAtualizada.getDataNascimento());
        return pessoaRepository.save(pessoa);
    }

    public void excluirPessoa(Long id) {
        Pessoa pessoa = consultarPessoa(id);
        pessoaRepository.delete(pessoa);
    }
}

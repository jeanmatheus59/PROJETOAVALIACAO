package com.jean.attornatus.controller;

import com.jean.attornatus.Exceptions.ResourceNotFoundException;
import com.jean.attornatus.domain.Endereco;
import com.jean.attornatus.domain.Pessoa;
import com.jean.attornatus.repository.PessoaRepository;
import com.jean.attornatus.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {
    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.criarPessoa(pessoa);
        return new ResponseEntity<>(novaPessoa, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> consultarPessoa(@PathVariable Long id) {
        Pessoa pessoa = pessoaService.consultarPessoa(id);
        return ResponseEntity.ok(pessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarPessoas() {
        List<Pessoa> pessoas = pessoaService.listarPessoas();
        return ResponseEntity.ok(pessoas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable Long id, @RequestBody Pessoa pessoaAtualizada) {
        Pessoa pessoa = pessoaService.editarPessoa(id, pessoaAtualizada);
        return ResponseEntity.ok(pessoa);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirPessoa(@PathVariable Long id) {
        pessoaService.excluirPessoa(id);
        return ResponseEntity.noContent().build();
    }
}

package com.jean.attornatus.controller;

import com.jean.attornatus.domain.Endereco;
import com.jean.attornatus.service.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas/{idPessoa}/enderecos")
public class EnderecoController {
    @Autowired
    private EnderecoService enderecoService;

    @PostMapping
    public ResponseEntity<Endereco> criarEndereco(@PathVariable Long idPessoa, @RequestBody Endereco endereco) {
        Endereco novoEndereco = enderecoService.criarEndereco(idPessoa, endereco);
        return new ResponseEntity<>(novoEndereco, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> consultarEndereco(@PathVariable Long idPessoa, @PathVariable Long id) {
        Endereco endereco = enderecoService.consultarEndereco(idPessoa);
        return ResponseEntity.ok(endereco);
    }

    @GetMapping
    public ResponseEntity<List<Endereco>> listarEnderecos(@PathVariable Long idPessoa) {
        List<Endereco> enderecos = enderecoService.listarEnderecos(idPessoa);
        return ResponseEntity.ok(enderecos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> editarEndereco(@PathVariable Long idPessoa, @PathVariable Long id, @RequestBody Endereco enderecoAtualizado) {
        Endereco endereco = enderecoService.editarEndereco(idPessoa, id, enderecoAtualizado);
        return ResponseEntity.ok(endereco);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEndereco(@PathVariable Long idPessoa, @PathVariable Long id) {
        enderecoService.excluirEndereco(idPessoa, id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/principal")
    public ResponseEntity<Endereco> tornarEnderecoPrincipal(@PathVariable Long idPessoa, @PathVariable Long id) {
        Endereco endereco = enderecoService.definirEnderecoPrincipal(idPessoa, id);
        return ResponseEntity.ok(endereco);
    }
}

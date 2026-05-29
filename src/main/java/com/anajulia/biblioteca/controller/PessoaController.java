package com.anajulia.biblioteca.controller;

import com.anajulia.biblioteca.model.Pessoa;
import com.anajulia.biblioteca.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping
    public ResponseEntity<Pessoa> criar(@RequestBody Pessoa pessoa) {
        Pessoa novaPessoa = pessoaService.salvar(pessoa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaPessoa);
    }

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodos() {
        return ResponseEntity.ok(pessoaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(pessoaService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return ResponseEntity.ok(pessoaService.atualizar(id, pessoa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        pessoaService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    // Rota especial para fazer Empréstimo de um livro
    @PostMapping("/{idPessoa}/emprestar/{idLivro}")
    public ResponseEntity<Pessoa> emprestarLivro(@PathVariable Long idPessoa, @PathVariable Long idLivro) {
        return ResponseEntity.ok(pessoaService.emprestarLivro(idPessoa, idLivro));
    }

    // Rota especial para fazer a Devolução de um livro
    @PostMapping("/{idPessoa}/devolver/{idLivro}")
    public ResponseEntity<Pessoa> devolverLivro(@PathVariable Long idPessoa, @PathVariable Long idLivro) {
        return ResponseEntity.ok(pessoaService.devolverLivro(idPessoa, idLivro));
    }
}

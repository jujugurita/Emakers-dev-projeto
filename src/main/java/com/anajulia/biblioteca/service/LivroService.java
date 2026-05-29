package com.anajulia.biblioteca.service;

import com.anajulia.biblioteca.model.Livro;
import com.anajulia.biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Livro buscarPorId(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));
    }

    public Livro atualizar(Long id, Livro livroAtualizado) {
        Livro livro = buscarPorId(id);
        livro.setNome(livroAtualizado.getNome());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setDataLancamento(livroAtualizado.getDataLancamento());
        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        Livro livro = buscarPorId(id);
        livroRepository.delete(livro);
    }
}

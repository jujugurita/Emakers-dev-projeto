package com.anajulia.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anajulia.biblioteca.model.Livro;
import com.anajulia.biblioteca.model.Pessoa;
import com.anajulia.biblioteca.repository.LivroRepository;
import com.anajulia.biblioteca.repository.PessoaRepository;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private ViaCepService viaCepService;

    // CRUD: Criar
    public Pessoa salvar(Pessoa pessoa) {
        if (!viaCepService.validarCep(pessoa.getCep())) {
            throw new RuntimeException("CEP inválido ou não encontrado na API externa!");
        }
        return pessoaRepository.save(pessoa);
    }

    // CRUD: Listar Todos
    public List<Pessoa> listarTodos() {
        return pessoaRepository.findAll();
    }

    // CRUD: Buscar por ID
    public Pessoa buscarPorId(Long id) {
        return pessoaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada!"));
    }

    // CRUD: Atualizar
    public Pessoa atualizar(Long id, Pessoa pessoaAtualizada) {
        Pessoa pessoa = buscarPorId(id);
        if (!viaCepService.validarCep(pessoaAtualizada.getCep())) {
            throw new RuntimeException("CEP inválido!");
        }
        pessoa.setNome(pessoaAtualizada.getNome());
        pessoa.setCpf(pessoaAtualizada.getCpf());
        pessoa.setCep(pessoaAtualizada.getCep());
        pessoa.setEmail(pessoaAtualizada.getEmail());
        pessoa.setSenha(pessoaAtualizada.getSenha());
        return pessoaRepository.save(pessoa);
    }

    // CRUD: Deletar
    public void deletar(Long id) {
        Pessoa pessoa = buscarPorId(id);
        pessoaRepository.delete(pessoa);
    }

    // REGRA DE NEGÓCIO: Empréstimo de Livro
    public Pessoa emprestarLivro(Long idPessoa, Long idLivro) {
        Pessoa pessoa = buscarPorId(idPessoa);
        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        // Verifica se o livro já não está emprestado para essa pessoa
        if (pessoa.getLivrosEmprestados().contains(livro)) {
            throw new RuntimeException("Esta pessoa já está com este livro emprestado!");
        }

        pessoa.getLivrosEmprestados().add(livro);
        return pessoaRepository.save(pessoa);
    }

    // REGRA DE NEGÓCIO: Devolução de Livro
    public Pessoa devolverLivro(Long idPessoa, Long idLivro) {
        Pessoa pessoa = buscarPorId(idPessoa);
        Livro livro = livroRepository.findById(idLivro)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado!"));

        if (!pessoa.getLivrosEmprestados().contains(livro)) {
            throw new RuntimeException("Este livro não estava emprestado para esta pessoa!");
        }

        pessoa.getLivrosEmprestados().remove(livro);
        return pessoaRepository.save(pessoa);
    }
}
package com.anajulia.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Table(name = "pessoa")
@Data
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pessoa")
    private Long idPessoa;

    private String nome;
    private String cpf;

    @Column(name = "data_nascimento")
    private java.time.LocalDate dataNascimento;

    private String telefone;
    private String cep;
    private String email;
    private String string; // Campo utilizado para simular a senha

    @ManyToMany
    @JoinTable(
        name = "emprestimo",
        joinColumns = @JoinColumn(name = "id_pessoa"),
        inverseJoinColumns = @JoinColumn(name = "id_livro")
    )
    private List<Livro> livrosEmprestados;

    // --- Métodos Manuais de Acesso (Getters e Setters Explicítos) ---
    public Long getIdPessoa() { return idPessoa; }
    public void setIdPessoa(Long idPessoa) { this.idPessoa = idPessoa; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public java.time.LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(java.time.LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return string; }
    public void setSenha(String senha) { this.string = senha; }

    public List<Livro> getLivrosEmprestados() { return livrosEmprestados; }
    public void setLivrosEmprestados(List<Livro> livrosEmprestados) { this.livrosEmprestados = livrosEmprestados; }
}
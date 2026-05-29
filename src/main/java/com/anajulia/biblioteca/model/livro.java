package com.anajulia.biblioteca.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "livro")
@Data
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_livro")
    private Long idLivro;

    private String nome;
    private String autor;

    @Column(name = "data_lancamento")
    private java.time.LocalDate dataLancamento;

    // Métodos manuais para garantir que o compilador não falhe
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public java.time.LocalDate getDataLancamento() { return dataLancamento; }
    public void setDataLancamento(java.time.LocalDate dataLancamento) { this.dataLancamento = dataLancamento; }
}

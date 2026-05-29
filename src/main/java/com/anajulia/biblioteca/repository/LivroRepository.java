package com.anajulia.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.anajulia.biblioteca.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
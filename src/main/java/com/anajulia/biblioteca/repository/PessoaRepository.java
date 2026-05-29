package com.anajulia.biblioteca.repository;

import com.anajulia.biblioteca.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Método necessário para buscar a pessoa pelo e-mail durante o login
    Optional<Pessoa> findByEmail(String email);
}
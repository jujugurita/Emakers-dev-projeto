package com.anajulia.biblioteca.security;

import com.anajulia.biblioteca.model.Pessoa;
import com.anajulia.biblioteca.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Encontra o usuário pelo email cadastrado
        Pessoa pessoa = pessoaRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o e-mail: " + email));

        // Retorna o usuário formatado para o Spring Security entender
        return new User(pessoa.getEmail(), pessoa.getSenha(), new ArrayList<>());
    }
}

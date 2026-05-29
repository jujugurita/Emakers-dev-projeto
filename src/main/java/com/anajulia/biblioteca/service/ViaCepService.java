package com.anajulia.biblioteca.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class ViaCepService {

    public boolean validarCep(String cep) {
        try {
            // Limpa o CEP tirando traços ou espaços
            String cepLimpo = cep.replaceAll("[^0-9]", "");
            String url = "https://viacep.com.br/ws/" + cepLimpo + "/json/";
            
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> resposta = restTemplate.getForObject(url, Map.class);
            
            // Se a API do ViaCEP retornar "erro": true, significa que o CEP não existe [cite: 75]
            if (resposta != null && resposta.containsKey("erro")) {
                return false;
            }
            return resposta != null;
        } catch (Exception e) {
            return false; // Se der qualquer erro na requisição, consideramos inválido [cite: 76]
        }
    }
}
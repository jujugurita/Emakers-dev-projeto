# Sistema de Gerenciamento de Biblioteca

Este projeto é uma API REST desenvolvida para o processo seletivo da Emakers. O sistema foi criado para gerenciar o funcionamento de uma biblioteca, controlando o cadastro de usuários (pessoas), o acervo de livros e o registro de empréstimos e devoluções.

## Stack Tecnológica & Arquitetura

O projeto foi estruturado seguindo as melhores práticas de desenvolvimento corporativo em Java, utilizando uma arquitetura em camadas (Controller, Service, Repository, Model):

* **Java 17 & Spring Boot 3.x** — Core da aplicação e ecossistema principal.
* **Spring Data JPA** — Abstração da camada de persistência e integração com o banco.
* **Spring Security** — Mecanismo de proteção de rotas e segurança global.
* **MySQL 8.0** — Banco de dados relacional de alta performance para armazenamento dos dados.
* **Flyway Migration** — Ferramenta de versionamento evolutivo do esquema do banco de dados.
* **Docker & Docker Compose** — Containerização completa da aplicação e infraestrutura de banco de dados.
* **Lombok** — Otimização de código através de anotações para geração de Getters, Setters e Construtores.
* **Swagger-UI (Springdoc OpenAPI 3)** — Geração automática de documentação interativa para os endpoints.

## Funcionalidades Principais & Regras de Negócio

### 1. Módulos de CRUD Completos
* **Pessoas:** Gerenciamento de dados dos usuários (clientes, bibliotecários, etc.).
* **Livros:** Catalogação e controle de dados das obras literárias.
* **Empréstimos:** Registro histórico de movimentações, saídas e devoluções.

### 2. Segurança Avançada (Spring Security)
* Sistema protegido por autenticação **HTTP Basic Auth**, validando de forma dinâmica as credenciais (`email` e `senha`) direto da tabela de Pessoas.
* Armazenamento seguro de senhas criptografadas no banco utilizando o algoritmo de hash **BCrypt**.
* Bloqueio global de acessos não autorizados nas rotas de negócio, mantendo públicas estritamente as rotas de documentação da API.

### 3. Lógica de Controle de Estoque Automático (Diferencial Técnico)
* **Validação de Saída:** Antes de concluir qualquer empréstimo, a camada de serviço (`EmprestimoService`) intercepta a requisição e verifica a disponibilidade física do livro. Se houver unidades em estoque, decrementa `-1` automaticamente e efetiva a operação. Caso contrário, a transação é abortada de forma segura emitindo uma exceção.
* **Validação de Retorno:** Assim que o endpoint de devolução é acionado, o sistema localiza a obra e incrementa `+1` de volta ao estoque geral, garantindo a consistência dos dados em tempo real.

## Estrutura de Pastas do Projeto

A organização dos arquivos segue o padrão recomendado pela comunidade Spring:

```text
src/main/java/com/anajulia/biblioteca/
│
├── config/          # Configurações gerais (Swagger, OpenAPI, etc.)
├── controller/      # Endpoints da API (Rotas HTTP que recebem as requisições)
├── model/           # Entidades mapeadas para o banco de dados (Tabelas)
├── repository/      # Interfaces de comunicação com o MySQL (Consultas)
├── security/        # Classes de controle do Spring Security e BCrypt
└── service/         # Camada contendo todas as regras de negócio do sistema


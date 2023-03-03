# Spring Boot Client CRUD REST API

Este é um projeto de uma API RESTful para um CRUD completo de clientes, contendo as cinco operações básicas:

* Busca paginada de clientes
* Busca de cliente por id
* Inserir novo cliente
* Atualizar cliente
* Deletar cliente

## Tecnologias utilizadas

* Spring Boot 3.0.3
* Java 11
* Maven
* H2 Database
* Spring Data JPA
* Spring Web

## Endpoints

A API possui os seguintes endpoints:

* `GET /clients`: retorna uma lista paginada de clientes.
  Parâmetros:
  - `page` (opcional, padrão 0): número da página a ser retornada.
  - `linesPerPage` (opcional, padrão 12): número de clientes por página.
  - `direction` (opcional, padrão ASC): direção da ordenação (ASC ou DESC).
  - `orderBy` (opcional, padrão name): atributo a ser ordenado (name, cpf ou income).
  
* `GET /clients/{id}`: retorna um cliente por id.

* `POST /clients`: adiciona um novo cliente.
  Corpo da requisição (JSON):

* `PUT /clients/{id}`: atualiza um cliente existente.
Parâmetros:

* `DELETE /clients/{id}`: deleta um cliente por id.


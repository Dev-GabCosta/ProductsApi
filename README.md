
# Projeto de API de Gerenciamento de Produtos

Este projeto é uma API RESTful para gerenciamento de produtos. Ele permite criar, ler, atualizar e deletar produtos, além de realizar buscas por nome.

## Tecnologias utilizadas

* Java 11
* Spring Boot 2.x
* Maven
* JPA (Java Persistence API)
* Hibernate

## Modelagem de dados

O projeto utiliza a seguinte modelagem de dados:

* `ProductEntity`: representa um produto, com atributos `id`, `name`, `description`, `price`, `amount` e `category`.
* `ProductDto`: representa um produto, com atributos `id`, `name`, `description`, `price`, `amount` e `category`. É utilizado para transferência de dados.
* `Category`: é um enum que representa as categorias de produtos, com valores `ELECTRONICS`, `FASHION`, `BOOKS`, `TOYS` e `FURNITURE`.

## Validações

O projeto utiliza validações para garantir a consistência dos dados. As validações são realizadas utilizando anotações do pacote `jakarta.validation.constraints`.

* `@NotNull`: verifica se o campo não é nulo.
* `@Size`: verifica se o campo tem um tamanho válido.
* `@Positive`: verifica se o campo é um número positivo.
* `@PositiveOrZero`: verifica se o campo é um número positivo ou zero.

Exemplos de JSONs para as validações:

* `@NotNull`:
```json
{
  "name": null // erro: nome é obrigatório
}
```

* `@Size`:
```json
{
  "name": "a" // erro: nome deve ter de 3 a 100 caracteres
}
```

* `@Positive`:
```json
{
  "price": -1 // erro: preço deve ser maior que 0
}
```

* `@PositiveOrZero`:
```json
{
  "amount": -1 // erro: quantidade deve ser maior ou igual a 0
}
```

## Endpoints

O projeto oferece os seguintes endpoints:

* `POST /products`: cria um novo produto.
* `GET /products`: lista todos os produtos.
* `GET /products/{id}`: busca um produto por ID.
* `PATCH /products/{id}`: atualiza um produto.
* `DELETE /products/{id}`: deleta um produto.
* `GET /products/search/{name}`: busca produtos por nome.

## Exemplos de uso

* Criar um novo produto:
```json
POST /products HTTP/1.1
Content-Type: application/json

{
  "name": "Produto 1",
  "description": "Descrição do produto 1",
  "price": 10.99,
  "amount": 10,
  "category": "ELECTRONICS"
}
```

* Buscar um produto por ID:
```bash
GET /products/1 HTTP/1.1
```

* Atualizar um produto:
```json
PATCH /products/1 HTTP/1.1
Content-Type: application/json

{
  "name": "Produto 1 atualizado",
  "description": "Descrição do produto 1 atualizado",
  "price": 12.99,
  "amount": 15,
  "category": "ELECTRONICS"
}
```

* Deletar um produto:
```bash
DELETE /products/1 HTTP/1.1
```

* Buscar produtos por nome:
```bash
GET /products/search/produto HTTP/1.1
```

## Configuração do ambiente

Para executar o projeto, é necessário ter Java 17 e IntelliJ instalados.

## Execução do projeto

1. faça clone do projeto em sua máquina.
2. Abra o projeto no IntelliJ.
3. Execute o projeto.
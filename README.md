# SQS-S3 API

Este projeto foi desenvolvido como parte de um desafio para implementar uma solução completa de CRUD para categorias e produtos, utilizando Spring Boot, MongoDB, AWS SNS, SQS e Lambda.

## Funcionalidades

Este projeto oferece uma API RESTful com as seguintes funcionalidades:

### Categorias

- **Criar Categoria**
    - **Método**: `POST`
    - **Rota**: `/api/category`
    - **Descrição**: Cria uma nova categoria no sistema e publica um evento na fila.

- **Listar Categorias**
    - **Método**: `GET`
    - **Rota**: `/api/category`
    - **Descrição**: Retorna todas as categorias cadastradas.

- **Atualizar Categoria**
    - **Método**: `PUT`
    - **Rota**: `/api/category/{id}`
    - **Descrição**: Atualiza uma categoria existente e publica um evento na fila.

- **Deletar Categoria**
    - **Método**: `DELETE`
    - **Rota**: `/api/category/{id}`
    - **Descrição**: Remove uma categoria existente do sistema.

### Produtos

- **Criar Produto**
    - **Método**: `POST`
    - **Rota**: `/api/product`
    - **Descrição**: Cria um novo produto no sistema e publica um evento na fila.

- **Listar Produtos**
    - **Método**: `GET`
    - **Rota**: `/api/product`
    - **Descrição**: Retorna todos os produtos cadastrados.

- **Atualizar Produto**
    - **Método**: `PUT`
    - **Rota**: `/api/product/{id}`
    - **Descrição**: Atualiza um produto existente e publica um evento na fila.

- **Deletar Produto**
    - **Método**: `DELETE`
    - **Rota**: `/api/product/{id}`
    - **Descrição**: Remove um produto existente do sistema.

## Arquitetura da Solução

A solução foi projetada para que, ao criar ou atualizar uma categoria ou produto, os dados sejam:

1. **Salvos no MongoDB**: Utilizando o Spring Data MongoDB para persistência dos dados.
2. **Publicados em uma fila SNS/SQS**: Após a persistência, um evento é publicado em uma fila SNS/SQS da AWS.
3. **Consumidos por uma Lambda**: A Lambda é responsável por consumir os eventos da fila.
4. **Salvos em um S3**: Finalmente, os dados processados pela Lambda são salvos em um bucket S3 da AWS.

Essa arquitetura garante que os dados sejam armazenados de forma redundante e estejam disponíveis tanto no MongoDB quanto no S3, além de permitir a comunicação assíncrona e desacoplada entre os serviços.

## Tecnologias Utilizadas

- **Spring Boot**: Framework principal para desenvolvimento da API.
- **MongoDB**: Banco de dados NoSQL utilizado para persistência.
- **AWS SNS/SQS**: Fila utilizada para comunicação entre os serviços.
- **AWS Lambda**: Função utilizada para processar os eventos da fila.
- **AWS S3**: Armazenamento utilizado para salvar os dados processados.

## Como Executar

1. Clone o repositório para a sua máquina local.
2. Configure as credenciais da AWS para que o Spring Boot possa acessar o SNS, SQS e S3.
3. Configure a conexão com o MongoDB no arquivo `application.properties`.
4. Execute o projeto utilizando o Maven ou seu IDE preferido.

```bash
mvn spring-boot:run

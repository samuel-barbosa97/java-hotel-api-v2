# Hotel API

Bem-vindo à API do Hotel, um projeto Spring Boot para gerenciar clientes e reservas em um hotel.

## Actions

[![Java CI with Gradle](https://github.com/samuel-barbosa97/java-hotel-api-v2/actions/workflows/gradle.yml/badge.svg?branch=main)](https://github.com/samuel-barbosa97/java-hotel-api-v2/actions/workflows/gradle.yml)

## Configuração

### Banco de Dados

A aplicação utiliza o PostgreSQL como banco de dados. Certifique-se de ter um servidor PostgreSQL em execução e atualize as configurações de banco de dados no arquivo `src/main/resources/application.yml` conforme necessário:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/hoteldb
    username: postgres
    password: admin
    driver-class-name: org.postgresql.Driver
    hikari:
      connection-timeout: 20000
      maximum-pool-size: 5
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
    open-in-view: false
  sql:
    init:
      platform: postgres
    data:
      platform: postgres
  liquibase:
    enabled: false  # Desativa o Liquibase para evitar conflitos com o Hibernate durante a inicialização
```

### Spring Boot Actuator

A aplicação utiliza o Spring Boot Actuator para fornecer endpoints prontos para produção. Você pode acessar esses endpoints para obter informações sobre o estado da aplicação, métricas e muito mais.

Para acessar os endpoints do Spring Boot Actuator, vá para `http://localhost:8080/actuator`. Certifique-se de configurar corretamente a segurança para proteger esses endpoints em um ambiente de produção.

Para uma visão mais visual e interativa dos dados fornecidos pelo Spring Boot Actuator, você pode configurar o Spring Boot Admin. Consulte as instruções no código para adicionar a dependência e configurar o cliente Spring Boot Admin.

## Endpoints

A API oferece os seguintes endpoints principais:

- `POST /api/clientes`: Cadastrar um novo cliente.
- `GET /api/clientes`: Obter todos os clientes cadastrados.
- `GET /api/clientes/{id}`: Obter detalhes de um cliente específico.
- `PUT /api/clientes/{id}`: Atualizar informações de um cliente.
- `DELETE /api/clientes/{id}`: Deletar um cliente.

- `POST /api/reservas`: Criar uma nova reserva.
- `GET /api/reservas`: Obter todas as reservas.
- `GET /api/reservas/{id}`: Obter detalhes de uma reserva específica.
- `PUT /api/reservas/{id}`: Atualizar informações de uma reserva.
- `DELETE /api/reservas/{id}`: Deletar uma reserva.

## Como Executar

1. Clone o repositório:

   ```bash
   git clone https://github.com/samuel-barbosa97/hotel-api.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd hotel-api
   ```

3. Execute a aplicação Spring Boot:

   ```bash
   ./gradlew bootRun
   ```

4. Acesse a API em `http://localhost:8080`.

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir problemas (issues) e enviar pull requests para melhorar esta aplicação.

## Licença

Este projeto está licenciado sob a [Licença MIT](LICENSE).

# Hotel API

Bem-vindo à API do Hotel, um sistema para gerenciar clientes e reservas em um hotel.

## Tecnologias Utilizadas

- Java 17
- Spring Framework 3.2.1
- PostgreSQL
- Gradle
- SLF4J para logs

## Configuração do Banco de Dados

A aplicação utiliza o PostgreSQL como banco de dados. Certifique-se de configurar as propriedades adequadas no arquivo `application.yml`:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/postgres
    username: postgres
    password: admin
    driver-class-name: org.postgresql.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    properties:
      hibernate:
        dialect: org.hibernate.dialect.PostgreSQLDialect
```

## Endpoints da API

### Clientes

- **Cadastrar Cliente (POST):** `/api/clientes`
  ```json
  {
    "nome": "Nome do Cliente",
    "email": "cliente@email.com",
    "telefone": "123456789"
  }
  ```

- **Obter Todos os Clientes (GET):** `/api/clientes`

- **Obter Cliente por ID (GET):** `/api/clientes/{id}`

- **Atualizar Cliente por ID (PUT):** `/api/clientes/{id}`
  ```json
  {
    "nome": "Novo Nome do Cliente",
    "email": "novo_cliente@email.com",
    "telefone": "987654321"
  }
  ```

- **Deletar Cliente por ID (DELETE):** `/api/clientes/{id}`

### Reservas

- **Criar Reserva (POST):** `/api/reservas`
  ```json
  {
    "dataInicio": "2023-01-01",
    "dataFim": "2023-01-10",
    "cliente": {
      "id": 1,
      "nome": "Nome do Cliente",
      "email": "cliente@email.com",
      "telefone": "123456789"
    },
    "tipoQuarto": "Quarto Duplo",
    "pagamentoConfirmado": false
  }
  ```

- **Obter Todas as Reservas (GET):** `/api/reservas`

- **Obter Reserva por ID (GET):** `/api/reservas/{id}`

- **Atualizar Reserva por ID (PUT):** `/api/reservas/{id}`
  ```json
  {
    "dataInicio": "2023-02-01",
    "dataFim": "2023-02-10",
    "cliente": {
      "id": 1,
      "nome": "Nome Atualizado do Cliente",
      "email": "novo_cliente@email.com",
      "telefone": "987654321"
    },
    "tipoQuarto": "Quarto VIP",
    "pagamentoConfirmado": true
  }
  ```

- **Deletar Reserva por ID (DELETE):** `/api/reservas/{id}`

## Em Construção

Este projeto está em construção, e mais funcionalidades serão adicionadas em breve.

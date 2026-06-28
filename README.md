# 🚀 Spring Boot Authentication API

Este projeto é uma API REST desenvolvida com Spring Boot, focada em autenticação e autorização de usuários utilizando Spring Security + JWT (JSON Web Token). O sistema conta com persistência em PostgreSQL e integração com um frontend separado.

## 🧰 Tecnologias utilizadas
- Java 17+
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- PostgreSQL
- Maven
- REST API
- Spring Boot Starter Test
- Frontend separado (HTML/JS ou framework externo)

## 📌 Funcionalidades
- Cadastro de usuários
- Login com autenticação JWT
- Geração de token de acesso
- Proteção de rotas (endpoints seguros)
- Validação de usuário autenticado
- Integração com banco de dados PostgreSQL
- Comunicação com frontend separado via HTTP

## 🔐 Como funciona a autenticação
O usuário realiza o cadastro em `/auth/register`, depois faz login em `/auth/login`. Se as credenciais estiverem corretas, o backend gera um JWT Token. Esse token é retornado para o frontend e deve ser enviado nas requisições protegidas no header:

Authorization: Bearer <token>

O Spring Security valida esse token antes de liberar o acesso às rotas protegidas.

## 📡 Endpoints principais

### Públicos
POST /auth/register  
POST /auth/login  

### Protegidos
GET /user/profile  
GET /admin/dashboard  

## 🗄️ Configuração do banco de dados (PostgreSQL)
spring.datasource.url=jdbc:postgresql://localhost:5432/seu_banco  
spring.datasource.username=seu_usuario  
spring.datasource.password=sua_senha  
spring.jpa.hibernate.ddl-auto=update  
spring.jpa.show-sql=true  

## ▶️ Como executar o projeto

Clone o repositório:
git clone https://github.com/seu-usuario/seu-repo.git  

Entre na pasta:
cd seu-repo  

Execute o projeto:
./mvnw spring-boot:run  
ou  
mvn spring-boot:run  

## 🌐 Frontend
O frontend consome a API através de requisições HTTP.

Exemplo de requisição:
fetch("http://localhost:8080/auth/login", {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    email: "teste@email.com",
    password: "123456"
  })
});

## 🔒 Segurança
- Autenticação stateless com JWT
- Spring Security protegendo rotas
- Senhas criptografadas com BCrypt
- Filtro de validação de token em todas as requisições

## 📁 Estrutura do projeto
src/
 ├── controller
 ├── service
 ├── repository
 ├── model
 ├── security
 ├── config
 └── dto

## 📌 Melhorias futuras
- Refresh Token
- Logout com blacklist de tokens
- Cadastro com validação de email
- Roles (ADMIN / USER)
- Deploy com Docker

## 👨‍💻 Autor
Antonio Marcos

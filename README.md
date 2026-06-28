# 🚀 Spring Boot Authentication API

Este projeto é uma API REST desenvolvida com Spring Boot, focada em autenticação e autorização de usuários utilizando Spring Security + JWT (JSON Web Token). O sistema conta com persistência em PostgreSQL e integração com um frontend separado.

## 🧰 Tecnologias utilizadas
- Java
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- H2 database(para testes)
- Maven
- REST API

## 📌 Funcionalidades
- Login com autenticação JWT
- Geração de token de acesso
- Proteção de rotas (endpoints seguros)
- Validação de usuário autenticado
- Integração com banco de dados

## 🔐 Como funciona a autenticação
O usuário realiza o cadastro em `/auth/register`, depois faz login em `/auth/login`. Se as credenciais estiverem corretas, o backend gera um JWT Token. Esse token é retornado para o frontend e deve ser enviado nas requisições protegidas no header:

Authorization: Bearer <token>

O Spring Security valida esse token antes de liberar o acesso às rotas protegidas.

## 📡 Endpoints principais

### Públicos
POST /auth/register  
POST /auth/login  


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

# 📚 Biblioteca API

API de gerenciamento de biblioteca desenvolvida em **Java 17 + Spring Boot 3** para fins de estudo em Bootcamp.

---

## 🚀 Tecnologias
- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 Database (memória)
- Swagger (Springdoc OpenAPI)

---

## 📂 Estrutura do Projeto

educa360/biblioteca-api
├─ src/main/java/br/com/bootcamp360/biblioteca
│ ├─ BibliotecaApiApplication.java
│ ├─ controllers/
│ ├─ services/
│ ├─ repositorys/
│ ├─ entitys/
│ ├─ converters/
│ ├─ configs/
│ └─ ...
├─ src/main/resources
│ ├─ application.yml
│ └─ data.sql
└─ pom.xm

---

## ⚙️ Como rodar o projeto

### Pré-requisitos
- [Java 17+](https://adoptium.net/)
- [Maven 3+](https://maven.apache.org/)

### Executando
```bash
# Clonar o repositório
git clone https://github.com/SEU_USUARIO/biblioteca-api.git
cd biblioteca-api

# Rodar o projeto
mvn spring-boot:run


🔗 Endpoints principais

📘 Livros
POST /api/v1/livros → Cadastrar livro
GET /api/v1/livros → Listar livros com paginação
GET /api/v1/livros/all → Listar todos os livros
GET /api/v1/livros/{id} → Buscar livro por ID
PUT /api/v1/livros/{id} → Atualizar livro
DELETE /api/v1/livros/{id} → Remover livro

👤 Usuários
POST /api/v1/usuarios → Criar usuário
GET /api/v1/usuarios → Listar usuários
GET /api/v1/usuarios/{id} → Buscar usuário por ID
PUT /api/v1/usuarios/{id} → Atualizar usuário
DELETE /api/v1/usuarios/{id} → Remover usuário

🔄 Empréstimos
POST /api/v1/emprestimos → Realizar empréstimo de livro
POST /api/v1/emprestimos/{id}/devolver → Devolver livro
GET /api/v1/emprestimos → Listar empréstimos
GET /api/v1/emprestimos/{id} → Detalhar empréstimo

🗄️ Banco de Dados H2
Console H2: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:biblioteca
User: sa
Password: (vazio)

📑 Documentação da API

Swagger UI: http://localhost:8080/swagger-ui.html

---

👨‍💻 Autor
Desenvolvido por Diego Pitoco no contexto do Bootcamp Educa360 🚀
# Agregador de Investimentos - Backend

Bem-vindo ao repositório do backend do Agregador de Investimentos!
Este projeto foi desenvolvido utilizando **Java 21, Spring Boot e MySQL** para criar uma **REST API CRUD** totalmente funcional.

## 🚀 Sobre o Projeto

Este backend foi desenvolvido como parte do curso "Agregador de Investimentos com Spring Boot" ministrado pela Build & Run. O curso está disponível gratuitamente no YouTube: [Playlist do Curso](https://www.youtube.com/playlist?list=PLxCh3SsamNs62j6T7bv6f1_1j9H9pEzkC).

Ao longo deste curso, desenvolvemos uma API robusta com funcionalidades essenciais para um sistema de investimentos. O projeto inclui:

- CRUD completo para usuários.
- Testes unitários com **JUnit** e **Mockito**.
- Relacionamentos entre entidades utilizando **JPA** e **Hibernate**.
- Consumo de uma API de cotações de ações com **OpenFeign**.

---

## 📌 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **MySQL**
- **Hibernate/JPA**
- **JUnit 5** e **Mockito**
- **OpenFeign**
- **Docker** (para subir o MySQL localmente)

---

## 📚 Conteúdo do Curso

### 1️⃣ Criando uma REST API CRUD

> Construímos uma REST API para manipular usuários no banco de dados MySQL com Spring Boot.

**Tópicos abordados:**

- Configuração inicial do Spring Boot
- Configuração do MySQL com Docker Compose
- Criação da camada **Controller, Service e Repository**
- Mapeamento de entidades com **Hibernate**
- Injeção de dependência e inversão de controle

🔗 **Commit**: [Clique aqui](https://github.com/joaomacaoli/investment-aggregator/commit/d53ec9c64695b5990f159c1e6d7400009753bb9d)

---

### 2️⃣ Testes Unitários com JUnit e Mockito

> Implementamos testes unitários para garantir a qualidade e confiabilidade do nosso código.

**Tópicos abordados:**

- Estrutura de testes unitários
- Padrão **Triple A** (Arrange, Act, Assert)
- Mocks com **Mockito** (doReturn, doNothing, doThrow, verify, ArgumentCaptor)
- Asserções com **JUnit 5**

🔗 **Repositório**: [Clique aqui](https://github.com/buildrun-tech/buil...)

---

### 3️⃣ Relacionamentos com JPA

> Implementamos diferentes tipos de relacionamentos no banco de dados utilizando Hibernate/JPA.

**Tópicos abordados:**

- **OneToOne** (1:1)
- **OneToMany** (1:N)
- **ManyToMany** (N:N)
- Criação de endpoints para manipular relacionamentos

🔗 **Repositório**: [Clique aqui](https://github.com/buildrun-tech/buil...)

---

### 4️⃣ Consumo de API de Cotações de Ações

> Integramos nossa aplicação com a API da **Brapi** para consumir dados do mercado financeiro.

**Tópicos abordados:**

- Configuração e uso do **OpenFeign**
- Implementação do **Client** para chamadas HTTP
- Mapeamento de DTOs e testes de integração

🔗 **Repositório**: [Clique aqui](https://github.com/buildrun-tech/buil...)

---

## 📌 Como Executar o Projeto

### 🛠️ Configuração Inicial

1. Clone este repositório:
   ```sh
   git clone https://github.com/joaomacaoli/investment-aggregator
   ```
2. Acesse a pasta do projeto:
   ```sh
   cd investment-aggregator
   ```
3. Configure as variáveis de ambiente no arquivo **.env**:
   ```sh
   MYSQL_USER=myuser
   MYSQL_PASSWORD=secret
   MYSQL_DATABASE=mydatabase
   ```
4. Suba o banco de dados MySQL via Docker:
   ```sh
   docker-compose up -d
   ```
5. Execute a aplicação:
   ```sh
   mvn spring-boot:run
   ```

---

## 🎯 Contribuição

Sinta-se à vontade para abrir issues e pull requests para melhorias!

---

## 📢 Contato

📸 **Instagram**: [@buildrun.tech](https://instagram.com/buildrun.tech)

---

Desenvolvido com ❤️ pela comunidade Build & Run 🚀

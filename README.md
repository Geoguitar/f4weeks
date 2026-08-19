# API Pentateuco & Parashot

Uma API RESTful desenvolvida em Java para consulta aos livros do Pentateuco (Gênesis, Êxodo, Levítico, Números e Deuteronômio), estruturada e demarcada para os estudos das **Parashot** (porções semanais da Torá).

## 🚀 Tecnologias Utilizadas

* **Java** (17+)
* **Spring Boot** (Web, Data JPA)
* **PostgreSQL** (Banco de dados relacional para persistência dos versículos)
* **H2 Database** (Banco de dados em memória exclusivo para execução rápida e isolada dos testes)
* **Docker & Docker Compose** (Containerização e orquestração do ambiente)
* **Gradle** (Gerenciamento de dependências e automação de build, substituindo o Maven)

## 🏛️ Modelo de Domínio

A entidade central do projeto mapeia os versículos e suas respectivas demarcações de Parashá:

```java
import jakarta.persistence.*;

@Entity
@Table(name = "versiculos")
public class Versiculo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String livro; // Gênesis, Êxodo, Levítico, Números, Deuteronômio
    private Integer capitulo;
    private Integer versiculo;
    
    @Column(columnDefinition = "TEXT")
    private String texto;

    // Coluna para demarcar a qual Parashá este versículo pertence
    @Column(name = "nome_parasha")
    private String nomeParasha; 
    
    // Getters, Setters e Construtores omitidos
}


🧪 Estratégia de Testes
Para evitar problemas de permissões com o daemon do Docker em ambientes Linux (erros de socket ao usar o Testcontainers), o projeto foi configurado para rodar os testes unitários e de integração utilizando o H2 Database.

Isso garante que os testes com anotações como @DataJpaTest rodem 100% isolados na memória da JVM de forma instantânea:



./gradlew clean test


🐳 Infraestrutura com Docker (Protótipo)
O projeto está configurado para rodar facilmente em contêineres, separando a aplicação do banco de dados relacional.

1. Dockerfile
Arquivo responsável por empacotar a aplicação Spring Boot gerada pelo Gradle:

Dockerfile
# Utilizando uma imagem leve do Java
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o .jar gerado pelo Gradle na pasta build para dentro do contêiner
COPY build/libs/*-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
2. docker-compose.yml
Orquestração da API junto ao banco de dados:

YAML
version: '3.8'

services:
  db:
    image: postgres:15-alpine
    container_name: pentateuco_db
    environment:
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: password
      POSTGRES_DB: pentateuco_db
    ports:
      - "5432:5432"
    volumes:
      - postgres_data:/var/lib/postgresql/data

  api:
    build: .
    container_name: pentateuco_api
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/pentateuco_db
      SPRING_DATASOURCE_USERNAME: postgres
      SPRING_DATASOURCE_PASSWORD: password
      SPRING_JPA_HIBERNATE_DDL_AUTO: update
    depends_on:
      - db

volumes:
  postgres_data:


🛣️ Endpoints Planejados (Proposta inicial)
GET /api/versiculos - Retorna a lista de versículos (com suporte à paginação).

GET /api/versiculos/parasha/{nomeParasha} - Busca todos os versículos correspondentes a uma Parashá específica.

GET /api/versiculos/livro/{nomeLivro} - Retorna os versículos de um determinado livro do Pentateuco.



⚙️ Como Executar o Projeto
Certifique-se de ter o Docker e o Docker Compose instalados em seu ambiente Linux/Ubuntu ou Windows.

Clone o repositório do projeto.

Gere o pacote da aplicação (via Gradle, ignorando os testes nesta etapa):

Bash
./gradlew clean build -x test
Suba os contêineres:

Bash
docker-compose up -d --build
A API estará disponível para uso em http://localhost:8083.
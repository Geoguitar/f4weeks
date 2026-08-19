# API Pentateuco & Parashot

Uma API RESTful desenvolvida em Java para consulta aos livros do Pentateuco (Gênesis, Êxodo, Levítico, Números e Deuteronômio), estruturada e demarcada para os estudos das **Parashot** (porções semanais da Torá).

## 🚀 Tecnologias Utilizadas

* **Java** (17+)
* **Spring Boot** (Web, Data JPA)
* **PostgreSQL** (Banco de dados relacional para persistência dos versículos)
* **Docker & Docker Compose** (Containerização e orquestração do ambiente)
* **Maven** (Gerenciamento de dependências)

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
```

## 🐳 Infraestrutura com Docker (Protótipo)

O projeto está configurado para rodar facilmente em contêineres, separando a aplicação do banco de dados relacional.

### 1. `Dockerfile`
Arquivo responsável por empacotar a aplicação Spring Boot:

```dockerfile
# Utilizando uma imagem leve do Java
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o .jar gerado pelo Maven para dentro do contêiner
COPY target/pentateuco-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### 2. `docker-compose.yml`
Orquestração da API junto ao banco de dados:

```yaml
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
```

## 🛣️ Endpoints Planejados (Proposta inicial)

* `GET /api/versiculos` - Retorna a lista de versículos (com suporte à paginação).
* `GET /api/versiculos/parasha/{nomeParasha}` - Busca todos os versículos correspondentes a uma Parashá específica.
* `GET /api/versiculos/livro/{nomeLivro}` - Retorna os versículos de um determinado livro do Pentateuco.

## ⚙️ Como Executar o Projeto

1. Certifique-se de ter o **Docker** e o **Docker Compose** instalados em seu ambiente Linux/Ubuntu ou Windows.
2. Clone o repositório do projeto.
3. Gere o pacote da aplicação (via Maven):
   ```bash
   mvn clean package -DskipTests
   ```
4. Suba os contêineres:
   ```bash
   docker-compose up -d --build
   ```
5. A API estará disponível para uso em `http://localhost:8083`.

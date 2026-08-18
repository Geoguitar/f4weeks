# f4weeks
Esse é um projeto com uma  estrutura de Mapear o Pentateuco (Torá) de forma relacional cruzando os versículos 
tradicionais com as Parashot (porções semanais) 
# API Pentateuco & Parashot

Uma API RESTful desenvolvida em Java para consulta aos livros do Pentateuco (Gênesis, Êxodo, Levítico, 
Números e Deuteronômio), 
estruturada e demarcada para os estudos das **Parashot** (porções semanais da Torá).

## 🚀 Tecnologias Utilizadas

* **Java** (17+)
* **Spring Boot** (Web, Data JPA)
* **PostgreSQL** (Banco de dados relacional para persistência dos versículos)
* **Docker & Docker Compose** (Containerização e orquestração do ambiente)
* **Gradle** (Gerenciamento de dependências e automação de builds)

## 🏛️ Modelo de Domínio

A entidade central do projeto mapeia os versículos e suas respectivas demarcações de Parashá:


🐳 Infraestrutura com Docker (Protótipo)
O projeto está configurado para rodar facilmente em contêineres, separando a aplicação do banco de dados relacional.

1. Dockerfile
   Arquivo responsável por empacotar a aplicação Spring Boot gerada pelo Gradle:


# Utilizando uma imagem leve do Java
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copia o .jar gerado pelo Gradle para dentro do contêiner
# O Gradle por padrão gera os artefatos na pasta build/libs/
COPY build/libs/pentateuco-api-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]

2. docker-compose.yml
   Orquestração da API junto ao banco de dados:


🛣️ Endpoints Planejados (Proposta inicial)
GET /api/versiculos - Retorna a lista de versículos (com suporte à paginação).

GET /api/versiculos/parasha/{nomeParasha} - Busca todos os versículos correspondentes a uma Parashá específica.

GET /api/versiculos/livro/{nomeLivro} - Retorna os versículos de um determinado livro do Pentateuco.

⚙️ Como Executar o Projeto
Certifique-se de ter o Docker e o Docker Compose instalados no seu sistema operacional (como o Ubuntu que você utiliza).

Clone o repositório do projeto.

Gere o pacote da aplicação (via Gradle):

./gradlew build -x test

(No Windows, utilize gradlew.bat build -x test)

Suba os contêineres:

docker-compose up -d --build

A API estará disponível para uso em http://localhost:8083.













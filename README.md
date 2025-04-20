# backend-clinica-pdw-ceub

Este projeto é o backend de um sistema criado para a realização do "Projeto 04" do trabalho de sistematização da matéria de Programação e Desenvolvimento Web no curso de Análise e Desenvolvimento de Sistemas (EAD) da UniCEUB.

## Características

O projeto foi criado em Java 21, utilizando o framework Quarkus. O Banco de Dados(BD) utilizado varia de acordo com a forma de execução. Em caso de execução do projeto no modo "desenvolvimento", é utilizado o H2, um BD relacional em memória, o que permite a sua rápida subida para execução do projeto. Já ao executar pelo docker, o BD utilizado é o MySQL na versão 9.2.0.

## Execução

*Para executar em modo de desenvolvimento, é necessário a instalação do Java 21 na máquina, bem como a devida configuração do "JAVA_HOME".*
*Para executar pelo Docker, é necessária a instalação e configuração básica do Docker*

Após a execução dos comandos, será possível encontrar uma página de documentação dos endpoints disponíveis em http://localhost:8080/api-docs

### Modo desenvolvimento

Em linux:

```
./mvnw install
./mvnw quarkus:dev
```

Em windows (CMD ou PowerShell):

```
.\mvnw.cmd install
.\mvnw.cmd quarkus:dev
```

### Modo Docker

Para executar pelo Docker, é necessário criar previamente um arquivo com o nome ```.env``` na raíz do projeto e adicionar as seguintes linhas:
```
DB_USERNAME=<Insira um nome de usuário. Ex: userDb>
DB_PASSWORD=<insira uma senha para o usuário. Ex: passwd>
DB_DATABASE=clinica
DB_PORT=3306
DB_ROOT_PASSWORD=root
```

Em linux:

```
./run/run.sh
```

Em windows (CMD ou PowerShell):

```
.\run\run.cmd
```

Aguarde até a seguinte mensagem aparecer no log para poder acessar a página de documentação dos endpoints:

```
api_clinica  | 2025-04-20 17:28:18,865 INFO  [io.quarkus] (main) backend-clinica-pdw-ceub 1.0.0-SNAPSHOT on JVM (powered by Quarkus 3.18.4) started in 24.041s. Listening on: http://0.0.0.0:8080
api_clinica  | 2025-04-20 17:28:18,880 INFO  [io.quarkus] (main) Profile prod activated. 
api_clinica  | 2025-04-20 17:28:18,881 INFO  [io.quarkus] (main) Installed features: [agroal, cdi, hibernate-orm, hibernate-orm-panache, hibernate-validator, jdbc-h2, jdbc-mysql, narayana-jta, rest, rest-jackson, smallrye-context-propagation, smallrye-health, smallrye-openapi, swagger-ui, vertx]
```

*Caso seja exibida uma mensagem de erro durante a subida do container "api_clinica", permaneça aguardando. Ela representa uma falha de conexão inicial, no qual o MySQL ainda está inicializando e ainda não está preparado para conexão. O Quarkus entra em fluxo de retentativa para estabelecer a comunicação entre os containers, e assim que esta ocorre, o log mencionado acima é exibido.*
# backend-clinica-pdw-ceub

Este projeto é o backend de um sistema criado para a realização do "Projeto 04" do trabalho de sistematização da matéria de Programação e Desenvolvimento Web no curso de Análise e Desenvolvimento de Sistemas (EAD) da UniCEUB.

## Características

O projeto foi criado em Java 21, utilizando o framework Quarkus. O Banco de Dados(BD) utilizado varia de acordo com a forma de execução. Em caso de execução do projeto no modo "desenvolvimento", é utilizado o H2, um BD relacional em memória, o que permite a sua rápida subida para execução do projeto. Já ao executar pelo docker, o BD utilizado é o MySQL na versão 9.2.0.

## Execução

*Para executar em modo de desenvolvimento, é necessário a instalação do Java 21 na máquina, bem como a devida configuração do "JAVA_HOME".*
*Para executar pelo Docker, é necessária a instalação e configuração básica do Docker*

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
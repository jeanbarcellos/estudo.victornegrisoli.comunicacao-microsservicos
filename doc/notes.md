# Anotações

### **Aula 013**

**Container PostgreSQL** -> https://hub.docker.com/_/postgres

<br>

**Container Auth-DB**

```
docker run --name auth-db -p 5432:5432 -e POSTGRES_DB=auth-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 postgres:11
```

**Container Product-DB**

```
docker run --name product-db -p 5433:5432 -e POSTGRES_DB=product-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 postgres:11
```

### **Aula 014**

**MongoDB** -> https://hub.docker.com/r/tutum/mongodb

**Container Sales-DB**

```
docker run --name sales-db -p 27017:27017 -p 28017:28017 -e MONGODB_USER="admin" -e MONGODB_DATABASE="sales" -e MONGODB_PASS="123456" tutum/mongodb
```

**Conexão no Mongoshell**

```
mongo "mongodb://admin:123456@localhost:27017/sales"
```

Caso você possua dificuldades em acessar o Mongo Shell (Windows), faça o seguinte:

1. Pesquise por "Variáveis de ambiente" na barra de busca do Windows e clique em "Editar as variáveis de ambiente do sistema"
2. Clique no botão "Variáveis de ambiente" no canto inferior direito.
3. Existirão ali 2 tabelas, para nós, o interessante será a de cima, procure a variável com valor "Path", caso não exista, crie clicando em "Novo"
4. Clique na variável "Path" e, dentro dela, clique em "Novo", você poderá adicionar dados em uma linha, no caso, o diretório onde está instalado o seu MongoDB
5. Geralmente, o caminho é "C:\Program Files\MongoDB\Server\{versão instalada}\bin", no meu caso, estou com a versão 4.2 instalada, então será "C:\Program Files\MongoDB\Server\4.2\bin"
6. Caso tenha dúvidas, digite "C:\Program Files\MongoDB\Server" na barra de pesquisa e entre no diretório, lá você verá qual o diretório que seu MongoDB está instalado.
7. Após setar a variável, apenas dê um "Ok" e saia.
8. Abra o CMD e digite o comando "mongo" e você conseguirá executar, agora, basta apenas conectar-se no banco de dados criado em container, ou caso tenha rodado o servidor localmente.

### **Aula 015**

Container RabbitMQ -> https://hub.docker.com/_/rabbitmq

```
docker run --name sales_rabbit -p 5672:5672 -p 25676:25676 -p 15672:15672 rabbitmq:3-management
```

### **Aula 016**

Execução docker-compose

```
docker-compose up --build
```

Para ignorar os logs, adicione a flag `-d`.

<br>

### **Aula 020**

No microsserviço `auth-api`

Criar o arquivo `Dockerfile` com o seguinte conteúdo:

```
FROM node:14
WORKDIR .
COPY package*.json ./
RUN yarn
COPY . .
EXPOSE 8080
CMD ["node", "app.js"]
```

Gerar imagem `Docker`, usando o comando:

```
docker image build -t auth-api .
```

Levantar um container com a imagem recém criada, usando o comando:

```
docker run --name auth-api -p 8080:8080 auth-api
```

<br>

No microsserviço `sales-api`

Criar o arquivo `Dockerfile` com o seguinte conteúdo:

```
FROM node:14
WORKDIR .
COPY package*.json ./
RUN yarn
COPY . .
EXPOSE 8082
CMD ["node", "app.js"]
```

Gerar imagem `Docker`, usando o comando:

```
docker image build -t sales-api .
```

Levantar um container com a imagem recém criada, usando o comando:

```
docker run --name sales-api -e PORT=8082 -p 8082:8082 sales-api
```

<br>

### **Aula 021**

No microsserviço `product-api`

Criar o arquivo `Dockerfile` com o seguinte conteúdo:

```
FROM node:14
WORKDIR .
COPY package*.json ./
RUN yarn
COPY . .
EXPOSE 8082
CMD ["node", "app.js"]
```

Gerar imagem `Docker`, usando o comando:

```
docker image build -t product-api .
```

Levantar um container com a imagem recém criada, usando o comando:

```
docker run --name product-api -p 8081:8081 product-api
```

<br>

### **Aula 021**

```
docker-compose up --build
```

Testando as apis:

- http://localhost:8080/api/status
- http://localhost:8081/api/status
- http://localhost:8082/api/status
- http://localhost:15672/

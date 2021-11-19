_Repositório apenas para estudo_

# Curso: Comunicação entre microsserviços

_Com API REST, Java 11, Spring Boot, PostgreSQL, Javascript ES6, Node.js, MongoDB, RabbitMQ, JWT, Docker e Heroku_

Códigos gerado a partir do curso '**Comunicação entre microsserviços**'

<br>

**Descrição:**

Neste curso, será aboradado o conceito teórico e prático sobre comunicação entre microsserviços e como aplicá-las. Praticamente iremos criar 3 APIs, duas delas com Node.js, MongoDB, Mongoose, Sequelize, PostgreSQL e JWT para autenticação. Iremos também criar uma API com Java 11 utilizando Spring Boot, PostgreSQL, Spring Data JPA, Spring Cloud OpenFeign e JWT, realizando a comunicação e integração entre essas duas tecnologias.

Iremos utilizar o RabbitMQ para a comunicação via filas de mensagens utilizando o protocolo AMQP com o intuito de criar uma comunicação assíncrona entre os serviços. Iremos também utilziar comunicação síncrona entre aplicações, ou melhor, chamadas a clients HTTP entre as APIs, integrando-as com as tecnologias FeignClient (Spring Boot) e Axios (Node.js).

Iremos também subir todas as nossas aplicações em containers Docker utilizando o Docker-compose. Por fim, ao termos tudo desenvolvido, subiremos toda a aplicação ao Heroku para ver tudo rodando em cloud e simular como seria um cenário real de desenvolvimento.

Neste curso, você verá uma abordagem teórica sobre arquitetura de microsserviços e arquitetura monolítica, suas comparações, vantagens e desvantagens, comunicação síncrona e assíncrona entre aplicações utilizando chamadas a API REST (HTTP) e filas de mensagens, além de conseguir compreender todos esses conceitos ao vê-los ocorrendo na prática. Iremos também detalhar os métodos e status HTTP, além também dos tipos de exchanges e filas de mensagens existentes no RabbitMQ para posterior implementação.

Iremos também implementar um básico de rastreabilidade de requisições entre microsserviços com logs nas APIs, IDs de requisições e iremos visualizar nossa rastreabilidade com as APIs no Heroku usando o add-on Coralogix Logging, que nos fornece uma interface do Kibana.

Ao fim deste curso você será capaz de criar suas próprias APIs e integrá-las da maneira que julgar necessário, com filas de mensagens ou chamadas REST, será capaz também de definir um ambiente de desenvolvimento e executar todos os serviços em containers ou em serviço em nuvem como Heroku, definindo suas próprias variáveis e arquivos de configuração de ambiente.

<br>

**O que será aprendido:**

- Comunicação síncrona entre serviços utilizando chamadas de API REST
- Comunicação assíncrona entre serviços utilizando AMQP com RabbitMQ e fila de mensagens
- Criação de containers para aplicações, bancos de dados e comunicação
- Criação de APIs utilizando Java 11 com Spring Boot e PostgreSQL
- Criação de APIs utilizando Javascript ES6, Nodejs, Expressjs e MongoDB
- Comunicação entre containers utilizando Docker-Compose
- Como realizar o deploy de microsserviços no Heroku
- Como proteger sua aplicação com variáveis de ambiente

<br>

**Há algum requisito ou pré-requisito para o curso?**

- Conhecimento da linguagem Java (1.8+)
- Conhecimento da linguagem Javascript (ES6)
- Conhecimentos básicos em SQL e NoSQL (MongoDB)
- Noções dos frameworks Spring Boot 2.x e ExpressJs
- Noções de conceitos de API REST

<br>

**Para quem é este curso:**

- Desenvolvedores Back-End (nível iniciante ou intermediário)
- Desenvolvedores Java (nível iniciante ou intermediário)
- Desenvolvedores Nodejs (nível iniciante ou intermediário)

<br>

**Instrutor:**

- [Victor Hugo Negrisoli](https://www.udemy.com/user/victor-hugo-negrisoli/)

**Referências:**

- https://www.udemy.com/course/comunicacao-entre-microsservicos
- https://github.com/vhnegrisoli/curso-udemy-comunicacao-microsservicos

## Conteúdo do curso

- Seção 01: Introdução - Apresentação, Arquitetura Monolítica e Microsserviços
- Seção 02: Teoria - Comunicação síncrona e assíncrona e RabbitMQ
- Seção 03: Preparação do ambiente de desenvolvimento com Docker
- Seção 04: Início da criação dos projetos dos microsserviços
- Seção 05: Microsserviço de Autenticação (Auth-API)
- Seção 06: Microsserviço de Produto (Product-API)
- Seção 07: Microsserviço de Vendas (Sales-API)
- Seção 08: Rastreabilidade e logs nas APIs
- Seção 09: Subindo todas as APIs com Docker-compose
- Seção 10: Subindo todas as aplicações no Heroku
- Seção 11: Isso é tudo ou tem mais sobre microserviços e sistema distribuídos?

<br>

## Tecnologias

- Java 11
- Spring Boot
- Javascript ES6
- Node.js 14
- ES6 Modules
- Express.js
- MongoDB (Container e Cloud MongoDB)
- API REST
- PostgreSQL (Container e Heroku Postgres)
- RabbitMQ (Container e CloudAMQP)
- Docker
- docker-compose
- JWT
- Spring Cloud OpenFeign
- Axios
- Heroku
- Coralogix Logging
- Kibana

<br>

## Visão geral do sistema

![Visão geral](https://raw.githubusercontent.com/jeanbarcellos/estudo.victornegrisoli.comunicacao-microsservicos/master/doc/images/001.png?token=ADSTN3GKW4NXVEIZM57G4JTBQ2VJC)

Detalhes das APIs:

- Auth-API:
  - API de Autenticação com Node.js 14, Express.js, Sequelize, PostgreSQL, JWT e Bcrypt.
- Sales-API:
  - API de Vendas com Node.js 14, Express.js, MongoDB, Mongoose, validação de JWT, RabbitMQ e Axios para clients HTTP.
- Product-API:
  - API de Produtos com Java 11, Spring Boot, Spring Data JPA, PostgreSQL, validação de JWT, RabbitMQ e Spring Cloud OpenFeign para clients HTTP.

### Fluxo de execução de um pedido

O fluxo para realização de um pedido irá depender de comunicações **síncronas** (chamadas HTTP via REST) e **assíncronas** (mensageria com RabbitMQ).

O fluxo está descrito abaixo:

- 01 - O início do fluxo será fazendo uma requisição ao endpoint de criação de pedido.
- 02 - O payload (JSON) de entrada será uma lista de produtos informando o ID e a quantidade desejada.
- 03 - Antes de criar o pedido, será feita uma chamada REST à API de produtos para validar se há estoque para a compra de todos os produtos.
- 04 - Caso algum produto não tenha estoque, a API de produtos retornará um erro, e a API de vendas irá lançar uma mensagem de erro informando que não há estoque.
- 05 - Caso exista estoque, então será criado um pedido e salvo no MongoDB com status pendente (PENDING).
- 06 - Ao salvar o pedido, será publicada uma mensagem no RabbitMQ informando o ID do pedido criado, e os produtos com seus respectivos IDs e quantidades.
- 07 - A API de produtos estará ouvindo a fila, então receberá a mensagem.
- 08 - Ao receber a mensagem, a API irá revalidar o estoque dos produtos, e caso todos estejam ok, irá atualizar o estoque de cada produto.
- 09 - Caso o estoque seja atualizado com sucesso, a API de produtos publicará uma mensagem na fila de confirmação de vendas com status APPROVED.
- 10 - Caso dê algum problema na atualização, a API de produtos publicará uma mensagem na fila de confirmação de vendas com status REJECTED.
- 11 - Por fim, a API de pedidos irá receber a mensagem de confirmação e atualizará o pedido com o status retornado na mensagem.

<br>

## Logs e Tracing da API

Todos os endpoints necessitam um header chamado **transactionid**, pois representará o ID que irá percorrer toda a requisição no serviço, e, caso essa aplicação chame outros microsserviços, esse **transactionid** será repassado. Todos os endpoints de entrada e saída irão logar os dados de entrada (JSON ou parâmetros) e o **transactionid**.

A cada requisição pra cada microsserviço, teremos um atributo **serviceid** gerado apenas para os logs desse serviço em si. Teremos então o **transactionid** que irá circular entre todos os microsserviços envolvidos na requisição, e cada microsserviço terá seu próprio **serviceid**.

Fluxo de tracing nas requisições:

**POST** - **/api/order** com **transactionid**: ef8347eb-2207-4610-86c0-657b4e5851a3

```
service-1:
transactionid: ef8347eb-2207-4610-86c0-657b4e5851a3
serviceid    : 6116a0f4-6c9f-491f-b180-ea31bea2d9de
|
| HTTP Request
|----------------> service-2:
                   transactionid: ef8347eb-2207-4610-86c0-657b4e5851a3
                   serviceid    : 4e1261c1-9a0c-4a5d-bfc2-49744fd159c6
                   |
                   | HTTP Request
                   |----------------> service-3: /api/check-stock
                                      transactionid: ef8347eb-2207-4610-86c0-657b4e5851a3
                                      serviceid    : b4fbc082-a49a-440d-b1d6-2bd0557fd189
```

Como podemos ver no fluxo acima, o **transactionid** ef8347eb-2207-4610-86c0-657b4e5851a3 manteve-se o mesmo nos 3 serviços, e cada serviço possui
seu próprio **serviceid**.

Exemplo de um fluxo completo chamando 5 serviços e gerando **transactionid** e **serviceid**:

![Tracing](https://raw.githubusercontent.com/jeanbarcellos/estudo.victornegrisoli.comunicacao-microsservicos/master/doc/images/002.png)

<br>
<br>

## Anotações

Testes na aula 42 e 43

```json
{
  "salesId": "6165b92addaf7fc9dd85dad0",
  "products": [
    {
      "productId": 1001,
      "quantity": 4
    },
    {
      "productId": 1002,
      "quantity": 2
    },
    {
      "productId": 1003,
      "quantity": 3
    }
  ]
}
```

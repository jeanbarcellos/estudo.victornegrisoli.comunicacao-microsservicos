# Anotações

### **Aula 013**

Container PostgreSQL -> https://hub.docker.com/_/postgres

<br>

**Container Auth-DB**

```
docker run --name auth-db -p 5432:5432 -e POSTGRES_DB=auth-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 postgres:11
```

**Container Product-DB**

```
docker run --name product-db -p 5433:5432 -e POSTGRES_DB=product-db -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=123456 postgres:11
```

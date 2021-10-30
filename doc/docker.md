# Alguns comandos Docker

Criar uma rede Docker

```
docker network create <nome-da-rede>
```

Baixar imagem do Dockerhub

```
docker pull <nome-da-imagem:tag>
```

Ver imagens

```
docker images
```

Criar/rodar um container de uma imagem

```
docker run -p <porta-externa>:<porta-interna> --name <nome-do-container> --network <nome-da-rede> <nome-da-imagem:tag>
```

Para container

```
docker container stop <nome-do-container>
```

Listar containers

```
docker ps

docker ps -a
```

Acompanhar logs do container em execução

```
docker logs -f <container-id>
```

Remover todos containers que estão parados

```
docker container prune
```

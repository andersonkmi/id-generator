# Sistema Pure - backend #

## Banco de dados ##

### Requisitos ###

* Instalar Docker Desktop v4.28.0 ou posterior - [site oficial](https://www.docker.com/products/docker-desktop/)

### Configuração da imagem local ###

Para configurar a imagem MySQL com as tabelas iniciais, os seguintes comandos devem ser executados:
```bash
$ cd docker
$ docker image build -t pure/puredb:1.0.0 .
```

Uma vez que imagem tenha sido criada, o container MySQL pode ser iniciado usando o comando abaixo:
```bash
$ docker container run --detach --name puredb --publish 3306:3306 pure/puredb:1.0.0
```
## Fazendo o build e executando a aplicação localmente

Para fazer o build, basta executar o comando abaixo. Não é necessário ter o banco de dados rodando pois os testes automatizados
usam um mock para a camada de dados.

```bash
$ gradle clean build
```

Uma vez que o build tenha sido concluído com sucesso, para executá-la basta rodar o comando abaixo:
```shell
$ java -Djasypt.encryptor.password=chave_criptografia_senha -jar ./build/libs/pure-backend-1.0.0.jar
```

Caso queira rodar a aplicação para fazer o debug remoto, o seguinte comando deve ser usado:
```shell
$ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -Djasypt.encryptor.password=chave_criptografia_senha -jar ./build/libs/pure-backend-1.0.0.jar
```
Com isso a porta 8000 pode ser usada para conectar o debugger remotamente.

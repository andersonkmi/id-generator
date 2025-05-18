# Id generator service #

## Database config ##

### Requirements ###

* [Docker Desktop](https://www.docker.com/products/docker-desktop/)

### Local container configuration ###
To configure the local Postgresql container, run the commands below.

#### Setup the image
```bash
$ cd pgsql/docker
$ docker image build -t codecraftlabs/idgenerator:1.0.0 .
```

#### Start the container
```bash
$ docker container run --detach --name idgenerator --publish 5432:5432 codecraftlabs/idgenerator:1.0.0
```

## Building and running the application

### Build
```bash
$ gradle clean build
```

### Run

```shell
$ java -jar ./build/libs/id-generator-1.0.0.jar
```

### Run with remote debugging enabled 

```shell
$ java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar ./build/libs/id-generator-1.0.0.jar
```
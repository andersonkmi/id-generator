# Id generator service #

This is a small service that can generate unique ids in a concurrent fashion. The idea is quite simple: use database sequences
to generate the numbers.

Distinct database sequences can be used for specific purposes so we can generate unique ids for different purposes without
interfering with each other.

## Id generation formats

When requesting a new id, the following formats are currently supported:
- default: returns the number as is without any formatting
- base64: returns a base-64 encoded version of the newly created id
- sha256: applies SHA-256 hashing on the id just generated
- luhn: returns a luhn valid number
- nonluhn: returns a non-luhn valid number

## Database config ##

### Requirements ###

* [Docker Desktop](https://www.docker.com/products/docker-desktop/)

### Local container configuration ###
To configure the local Postgresql container, run the commands below.

#### Setup the image
```bash
cd pgsql/docker
docker image build -t codecraftlabs/idgenerator:1.0.0 .
```

#### Start the container
```bash
docker container run --detach --name idgenerator --publish 5432:5432 codecraftlabs/idgenerator:1.0.0
```

## Building and running the application

### Build
```bash
gradle clean build
```

### Run

```shell
java -jar ./build/libs/id-generator-1.0.0.jar
```

### Run with remote debugging enabled 

```shell
java -Xdebug -Xrunjdwp:server=y,transport=dt_socket,address=8000,suspend=n -jar ./build/libs/id-generator-1.0.0.jar
```
# Local Initializer

Localhost API dependencies initialization

## RabbitMQ
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 -d rabbitmq:3.9-management

## MongoDB
docker run --name mongodb -d -p 27017:27017 mongo

## MySQL
docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=netcafe -e MYSQL_USER=user -e MYSQL_PASSWORD=user -p 33060:3306 -d mysql/mysql-server:5.7

MySQL terminal: fix permissions (user with password 'user')
docker exec -it mysql -u root -p
root
grant all on *.* to user@localhost identified by 'user';

## Application.properties

### MySQL

spring.datasource.url=jdbc:mysql://localhost:33060/netcafe
spring.datasource.username=user
spring.datasource.password=user
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

### MongoDB

spring.data.mongodb.uri=mongodb://localhost:27017
spring.data.mongodb.database=netcafe

## Strategy to auto update the schemas (create, create-drop, validate, update)

spring.jpa.hibernate.ddl-auto=update
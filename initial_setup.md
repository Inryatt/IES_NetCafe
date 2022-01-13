# Initial Setup

## API

The API uses a MySQL Docker as database. If you don't have it, create a docker container with a structure like that: 

docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=secret2 -p 33060:3306 -d mysql/mysql-server:5.7

### Cleaning the DB

If your DB isn't cleaned already, open the doker image CLI and follow these commands:

mysql -u root -p
-- Input password --
SET foreign_key_checks = 0;
show tables; -- to show every table

For each table: 

drop table nome_da_tabela;

And after dropping the tables:

SET foreign_key_checks = 1;

### Spring Boot

The "api" directory is a maven project, so it's necessary to install the used jars with mvn install.

In the "api/src/main/resources" directory (you may need to create "resources" folder"), change `application.properties`file to follow a structure like bellow, changing values accordingly to your database docker:

# MySQL
spring.datasource.url=jdbc:mysql://127.0.0.1:33060/quotes
spring.datasource.username=user
spring.datasource.password=user
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

# Strategy to auto update the schemas (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=update
## Data Generation

### Initial Data

The bellow steps are when the data already exists on the DB. If you don't already have it, go to "db_initializer", install the requirements, and then run `initialize_db.py`file. This will setup the base machines, users, softwares and locations for the application.

### Broker initializer

The Broker is the entity who will connect the Generator with the Parser Server. To initialize it, go to "broker" directory and run the command:

docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management

### Python Parser

The Parser will be responsible for filtering the data from the Generator (and eventually from the sensors) and sending it to the API.

To start it, go to "pythonparser" directory, install the requirements, and then run data_parser.py file.

### Python Generator

The Generator will constantly send Random Machine Usage data to the Parser trough the Broker.

To start it, go to "datagen" directory, install the requirements, and then run datagen.py file.
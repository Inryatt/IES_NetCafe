# Initial Setup

## API

The API utilizes the same docker used on classes for DB. If you don't have it, search in the guide how to initialize it. After these two steps, you can run the project in your IDE.

### Cleaning the DB

If your DB isn't cleaned already, open the image CLI and follow these commands:

```
mysql -u root -p
-- Input password --
SET foreign_key_checks = 0;
show tables; (to show every table)
```

For each table: 

`drop table nome_da_tabela;`

And after dropping the tables:

`SET foreign_key_checks = 1;`

### Spring Boot

Change `application.properties`file to follow a structure like bellow (according to your docker):

```
# MySQL
spring.datasource.url=jdbc:mysql://127.0.0.1:33060/quotes
spring.datasource.username=user
spring.datasource.password=user
spring.jpa.database-platform=org.hibernate.dialect.MySQL5InnoDBDialect

# Strategy to auto update the schemas (create, create-drop, validate, update)
spring.jpa.hibernate.ddl-auto=update
```

## Data Generation

### Initial Data

The bellow steps are when the data already exists on the DB. If you don't already have it, go to "db_initializer", install the requirements, and then run `initialize_db.py`file.

### Broker initializer

Go to "broker" directory and run the command:

```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
```

### Python Parser

Go to "pythonparser" directory, install the requirements, and then run `data_parser.py` file.

### Python Generator

Go to  "datagen" directory, install the requirements, and then run `datagen.py` file.




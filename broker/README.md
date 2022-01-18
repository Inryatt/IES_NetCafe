# How to use Docker Rabbit MQ

Use the commands 

```
docker pull rabbitmq:3-management
```

```
docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.9-management
```



## Address already in use error

Identify the rabbitmq PID and delete it:

```
sudo lsof -i -P -n
sudo kill RABBITPID
```


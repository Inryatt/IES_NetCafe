import pika
import json
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()
channel.queue_declare(queue="machine-usage")

channel.basic_publish(exchange='machine-usage-exchange',
                      routing_key='routing.key',
                      body=json.dumps({
                          "machineId": 1,
                          "userId": 1,
                          "timestampStart": 0,
                          # "timestampEnd": 1,
                          "cpuUsage": 0.5,
                          "gpuUsage": 0.6,
                          "networkUpUsage": 10,
                          "networkDownUsage": 25,
                          "powerUsage": 300,
                          "ramUsage": 0.7,
                          "diskUsage": 0.25,
                          "uptime": 1,
                          "softwareUsage": [1, 2, 3]
                      }))
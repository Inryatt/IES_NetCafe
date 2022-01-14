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
                          "timestampStart": 3,
                          # "timestampEnd": 1,
                          "cpuUsage": 1,
                          "gpuUsage": 0.5,
                          "networkUpUsage": 50,
                          "networkDownUsage": 50,
                          "powerUsage": 500,
                          "ramUsage": 0.5,
                          "diskUsage": 0.5,
                          "uptime": 0,
                          "softwareUsage": [2, 4]
                      }))

# {
#                           "machineId": 1,
#                           "userId": 1,
#                           "timestampStart": 1,
#                           # "timestampEnd": 1,
#                           "cpuUsage": 0.99,
#                           "gpuUsage": 0.99,
#                           "networkUpUsage": 100,
#                           "networkDownUsage": 100,
#                           "powerUsage": 300,
#                           "ramUsage": 0.99,
#                           "diskUsage": 0.99,
#                           "uptime": 2,
#                           "softwareUsage": [2, 4]
#                       }
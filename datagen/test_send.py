import pika
import json
connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
channel = connection.channel()
channel.queue_declare(queue="machine-usage")

channel.basic_publish(exchange='machine-usage-exchange',
                      routing_key='routing.key',
                      body=json.dumps({"sus":"very sussy"}))
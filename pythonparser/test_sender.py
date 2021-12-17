import pika, json

static_machine = {"id": 1, "usage": {"cpu": 2.27, "gpu": 4.56, "ram": 5.01, "disk": 0, "network_up": 3.17, "network_down": 2.62, "temp": 21.22, "programs": [{"id": 1, "name": "Adobe Photoshop", "type": "work"}], "status": 1}}

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='localhost'))
channel = connection.channel()

channel.queue_declare(queue='machine_usage')
print("Machine:", static_machine)

channel.basic_publish(exchange='', routing_key='machine_usage', body=json.dumps(static_machine))
print("Sent 'machine")
connection.close()
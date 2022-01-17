import pika, json

static_machine = {"id": 1, "timestamp": 1, "cpuUsage": 2.27, "gpuUsage": 4.56, "ramUsage": 5.01, "diskUsage": 0, "networkUpUsage": 3.17, "networkDownUsage": 2.62, "cpuTemp": 21.22, "gpuTemp": 22.23, "softwares": [{"id": 1}]}

connection = pika.BlockingConnection(
    pika.ConnectionParameters(host='rabbitmq'))
channel = connection.channel()

channel.queue_declare(queue='machine_usage')
print("Machine:", static_machine)

channel.basic_publish(exchange='', routing_key='machine_usage', body=json.dumps(static_machine))
print("Sent 'machine")
connection.close()
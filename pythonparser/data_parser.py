from typing import Dict, List
import pika, sys, os, json, requests

def generatePatch(dic : Dict, patches : List = [], prefix="") -> List:
    """Returns the JsonPatch of given dictionary."""
    for key,val in dic.items():
        if key == "id": continue
        new_prefix = prefix+"/"+key
        if isinstance(val, Dict):
            generatePatch(val, patches=patches, prefix=new_prefix)
        else:
            patches.append({"op": "replace", "path": new_prefix, "value": val})
    return patches

def main():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()

    channel.queue_declare(queue='machine_usage')

    def machine_callback(ch, method, properties, body):
        machine : Dict = json.loads(body)

        # Machine is something like this (Camila edition):
        # {'id': 1, 'usage': {'cpu': 2.27, 'gpu': 4.56, 'ram': 5.01, 
        # 'disk': 0, 'network_up': 3.17, 'network_down': 2.62, 'temp': 21.22, 
        # 'programs': [{'id': 1, 'name': 'Adobe Photoshop', 'type': 'work'}], 'status': 1}}

        jspatch = generatePatch(machine)
        print("jspatch:", jspatch)

        base_url = "localhost:8080/api/machines/"
        machine_id = machine.get("id")
        if machine_id is not None:
            print("POST (not yet) sent to Machine",machine_id,"!")
            #r = requests.patch(base_url + machine_id, data = jspatch)

    channel.basic_consume(queue='machine_usage', on_message_callback=machine_callback, auto_ack=True)

    print(' [*] Waiting for messages. To exit press CTRL+C')
    channel.start_consuming()

if __name__ == '__main__':
    try:
        main()
    except KeyboardInterrupt:
        print(' Interrupted')
        try:
            sys.exit(0)
        except SystemExit:
            os._exit(0)
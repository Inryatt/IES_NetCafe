import time
from typing import Dict, List
import pika, sys, os, json, requests

def generatePatch(dic : Dict, patches : List = [], prefix="") -> List:
    """Returns the JsonPatch of given dictionary."""
    for key, val in dic.items():
        if key == "softwares": continue
        if key == "id" and prefix == "": continue
        new_prefix = prefix + "/" + key
        # if isinstance(val, Dict):
        #     generatePatch(val, patches=patches, prefix=new_prefix)
        # else:
        patches.append({"op": "replace", "path": new_prefix, "value": val})
    return patches

def main():
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='rabbitmq'))
    channel = connection.channel()

    channel.queue_declare(queue='machine_usage')

    def machine_callback(ch, method, properties, body):
        machine : Dict = json.loads(body)
        lst = []
        # print("machine:", machine)

        jspatch = generatePatch(machine, patches=lst)
        #print(f"{jspatch = }")

        base_url = "http://api:8080/api/machines/"

        test_req = requests.get("http://api:8080/api/machines")
        print(test_req)
        machine_id = machine.get("id")
        if machine_id is not None:
            print("POST sent to Machine",machine_id,"!")
            r = requests.patch(base_url + str(machine_id), headers={'content-type': 'application/json-patch+json'}, json=jspatch)

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
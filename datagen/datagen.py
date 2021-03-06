import sys
import numpy as np
import random as rm
import json
import time
import pika

if len(sys.argv)>1 and sys.argv[1]=='test':
    pass
else:
    connection = pika.BlockingConnection(pika.ConnectionParameters(host='rabbitmq'))
    # connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))
    channel = connection.channel()
    channel.queue_declare(queue="machine-usage")

users = {i:True for i in range(0, 5)}
base_url = "http://api:8080/api/machines/"
# base_url = "http://localhost:8080/api/machines/"


with open("software_list.json") as f:
    program_list = json.load(f)


class program():
    def __init__(self, name, id, type):
        self.name = name
        self.id = id
        self.type = type

class Machine():
    def __init__(self, specs, id, location, base_temp):
        # Internal Status Data
        self.status = 0  # 0 -> Off 1 ->On 2 -> Unavailable (temporarily)
        # [("cpu",+0.4)], for instance, will make cpu usage go up 0.4(%) by cycle
        self.event_status = []

        self.sus_eventstatus = []
        # Data that will be sent along to the Broker
        self.id = id
        self.specs = {}
        self.specs["CPU"] = specs["CPU"]
        self.specs["GPU"] = specs["GPU"]
        self.specs["RAM"] = specs["RAM"]
        self.specs["Disk"] = specs["Disk"]
        self.specs["OS"] = specs["OS"]
        self.specs["Name"] = specs["Name"]
        self.location = location
        self.coords = (None, None)  # (x,y)
        self.current_user = None
        self.usage = {}

        self.usage["cpu"] = 0
        self.usage["gpu"] = 0
        self.usage["ram"] = 0
        self.usage["disk"] = 0
        self.usage["network_down"] = 0
        self.usage["network_up"] = 0

        # Can be slightly above room temp
        self.usage['cpu_temp'] = base_temp + rm.random()*3
        self.usage['gpu_temp'] = base_temp + rm.random()*3
        self.usage['power'] = 300
        self.start_time = time.time()
        self.programs = []

    def crash(self):
            self.usage = {}
            self.usage["cpu"] = 0
            self.usage["gpu"] = 0
            self.usage["ram"] = 0
            self.usage["disk"] = 0
            self.usage["network_down"] = 0
            self.usage["network_up"] = 0
            self.usage['cpu_temp'] = 20 + rm.random()*3
            self.usage['gpu_temp'] = 23 + rm.random()*3
            self.usage['power'] = 0

            self.programs = []
            users[self.current_user] = True
            self.current_user = None
        
            self.event_status = []
            self.sus_eventstatus = []
            self.status=2 # Crash

    def event(self):
        rng = rm.random()
        alert_p = 0.7
        sus_p = 0.1
        crash_p = 0.2
        possible_events = ['cpu', 'gpu', 'ram', 'cpu_temp','cpu_temp', 'disk']
        if rng < alert_p:
            self.event_status.append(
                (possible_events[rm.randint(0, len(possible_events)-1)], rm.random()*10))
        elif rng<alert_p+sus_p:
            pass
            possible_events=['torrent_up','torrent_down','crypto_mining','sus_program']
            ev = rm.randint(0,3)
            ev=possible_events[ev]
            if ev=='torrent_up':
                 self.event_status.append(
                     ('network_up',rm.random()*100)
                 )
            if rng < alert_p:
                # add a random event
                possible_events = ['cpu', 'gpu', 'ram', 'cpu_temp','cpu_temp', 'disk']

                self.event_status.append(
                (possible_events[rm.randint(0, len(possible_events)-1)], rm.random()*30))
            elif ev == 'torrent_down':
                self.sus_eventstatus.append(
                     ('network_down',rm.random()*100)
                 )

            elif ev == 'crypto_mining':
                self.event_status = []
                self.sus_eventstatus.append(
                     ('gpu_temp',rm.random()*10)
                 )
                self.sus_eventstatus.append(
                     ('cpu_temp',rm.random()*10)
                 )
                self.sus_eventstatus.append(
                     ('gpu',rm.random()*40)
                 )
                self.sus_eventstatus.append(
                     ('power',rm.random()*100+40)
                 )
            else: #sus program open
                sus_programs=[
                    {
                        "id":999,
                        "name":"WannaCry",
                        "type":"Malware"
                    },                     {
                        "id":998,
                        "name":"MimiKatz",
                        "type":"Malware"
                    },
                     {
                        "id":997,
                        "name":"Mirai Networks Ltd",
                        "type":"Malware"
                    },
                     {
                        "id":996,
                        "name":"Async_RAT",
                        "type":"Malware"
                    },
                    {
                        "id":995,
                        "name":"stage1.exe",
                        "type":"Malware"
                    }
                ]
                ev = rm.randint(0,len(sus_programs)-1)

                self.programs.append(sus_programs[ev])
                
        else:
            self.crash()
            pass

    def turn_on(self):
        #print(f"current status: {self.status}")
        if self.status==1:
            # already on
            pass
        else:
            found =False
            for user in users:
                if users[user]:
                    self.current_user = user
                    users[user] = False
                    found =True
                    break
            if not found:
                return     # no free users
            self.start_time = time.time()

            #print("set status to 1")
            self.status = 1
            #print(f"machine {self.id} status {self.status}")

    def turn_off(self):
        if self.status==1:
            users[self.current_user] = True
            self.current_user= None
            self.status = 0
            self.usage = {}
            self.usage["cpu"] = 0
            self.usage["gpu"] = 0
            self.usage["ram"] = 0
            self.usage["disk"] = 0
            self.usage["network_down"] = 0
            self.usage["network_up"] = 0
            self.usage['cpu_temp'] = 20 + rm.random()*3
            self.usage['gpu_temp'] = 23 + rm.random()*3
            self.usage['power'] = 0

            self.programs = []
            self.event_status = []
            self.sus_eventstatus = []
        elif self.status==2:
            users[self.current_user] = True
            self.current_user= None

    def close_program(self):
        if len(self.programs) < 2:
            self.turn_off()
        else:
            self.programs.pop(0)  # close last running

    def open_program(self):
        if len([ p for p in self.programs if p['type']!= "malware"]) > 4:  # Too many programs running, replace event with close
            self.close_program()
        else:
            used_types = [prog["type"] for prog in self.programs]
            #print(used_types)
            valid_prog=[]
            for prog in program_list:
                add=True
                for t in used_types:
                    #print(f"is {prog['type']} the same as {t}???? {'true' if prog['type']==t  else 'False'}")
                    if prog["type"]==t:
                        add=False
                        break
                if add:
                    valid_prog.append(prog)

            #print(valid_prog)
            #valid_programs = [prog for prog in program_list if (
            #    (prog["type"] == ptype or ptype == None) and  not (prog["type"]  in used_types))]
            if len(valid_prog)<2:
                self.close_program()
            else:
                rng = rm.randint(0, len(valid_prog)-1)
                self.programs.append(valid_prog[rng])

    def machine_off_loop(self):
        rng = rm.random()
        if rng < 0.4:
            self.turn_on()
            self.open_program()

    def deal_with_ongoing_events(self):
        if self.event_status:
            # Ongoing event:
            # Delete some randomly (30% chance)
            self.event_status = [
                ev for ev in self.event_status if rm.random() < 1]
            for ev in self.event_status:
                self.usage[ev[0]] += ev[1]
        
        if self.sus_eventstatus:
            for ev in self.sus_eventstatus:
                self.usage[ev[0]] += ev[1]*np.log(self.usage[ev[0]]) if self.usage[ev[0]]> 1 else ev[1]
        if not self.sus_eventstatus and not self.event_status:
            for prog in self.programs:
                if prog["type"]=="Malware":
                    r = rm.random()
                    if r < 0.3: # Detect Malware, set as off and unavailable
                        self.status=2
                        self.turn_off()
            return

    def fluctuate_usage(self):
        tmp_us=['cpu','gpu','ram','cpu_temp','gpu_temp','network_up','network_down','disk']
        for spec in tmp_us:
            rng = (rm.random()*10-5)+np.log(len(self.programs) * \
                rm.random()*3)  # [-5,5]
            self.usage[spec] += rng
            self.usage[spec] = round(self.usage[spec], 2)
            if self.usage[spec] < 0:
                self.usage[spec] = 0
            if self.usage[spec] > 100:
                if spec == "network_down" or spec == "network_up":
                    pass
                elif spec == "cpu_temp" or spec=="gpu_tmp":
                    self.turn_off()
                    self.status=2
                else:
                    self.usage[spec] = 100
        if len(self.sus_eventstatus) ==0:
            self.usage['power'] = len(self.programs)*10 + rm.randint(30,100)
        else:
            self.usage['power'] = self.usage['power'] + (rm.random()*30-10)

    def machine_loop(self):
        if self.status==0:
            self.machine_off_loop()
        elif self.status ==2:
            rng=rm.random()
            if rng<0.5:         # Can become available or stay poofed
                self.status=0
        else:
            # Take care of ongoing events

            self.deal_with_ongoing_events()
            self.fluctuate_usage()
            #self.print_usage()
            
            rng = rm.random()
            #rng = 1
            #pass_p = 0.75
            #open_p = 0.1
            #event_p = 0.05
            #close_p = 0.1
            pass_p = 0.70
            open_p = 0.1
            event_p = 0.05
            close_p = 0.1
            if rng < pass_p:                 # Pass
                return
            elif rng < pass_p+open_p:         # Open Program
                self.open_program()
            elif rng < pass_p+open_p+close_p:  # Close Program
                self.close_program()
            else:                           # EVENT
                self.event()

    def print_usage(self):
        print(f"""
        Machine: {self.specs["Name"]}
        status: {self.status}   | 0 -> Off
                    | 1 -> On
                    | 2 -> Unavailable
        
        id: {self.id}
        cpu: {self.usage['cpu']}%
        gpu: {self.usage['gpu']}%
        ram: {self.usage['ram']}%
        cpu temp: {self.usage['cpu_temp']}??C
        gpu temp: {self.usage['gpu_temp']}??C
        power: {self.usage['power']}W

        disk: {self.usage['disk']}%
        network (up): {self.usage['network_up']} MB/s
        network (down) {self.usage['network_down']} MB/s
        running: {self.programs}

        user: {self.current_user}
        ongoing events: {self.event_status}
        sus ongoing events: {self.sus_eventstatus}
        """)



#{
# "id": 1,
# "timestamp": 1, 
# "cpuUsage": 2.27, 
# "gpuUsage": 4.56, 
# "ramUsage": 5.01, 
# "diskUsage": 0, 
# "networkUpUsage": 3.17, 
# "networkDownUsage": 2.62, 
# "cpuTemp": 21.22, 
# "gpuTemp": 22.23, 
# "softwares": [
#   {
#       "id": 1
#   }
#   ]
# }

    def export_data(self):
        obj = {
            'machineId':self.id,
            'timestamp':int(time.time()),
            'cpuUsage':self.usage['cpu'],
            'gpuUsage':self.usage['gpu'],
            'ramUsage':self.usage['ram'],
            'diskUsage':self.usage['disk'],
            'networkUpUsage':self.usage['network_up'],
            'powerUsage':self.usage['power'],
            'networkDownUsage':self.usage['network_down'],
            'cpuTemp':round(self.usage['cpu_temp'],2),
            'gpuTemp':round(self.usage['gpu_temp'],2),
            # 'softwareUsage':[{'id':prog['id']} for prog in self.programs],
            'softwareUsage': [prog['id'] for prog in self.programs],
            'status':self.status,
            'userId':self.current_user
#           'power':self.usage['power']
        }
        
        return json.dumps(obj)
        #channel.basic_publish(exchange='',
        #              routing_key='datagen',
        #              body=json.dumps(obj))
        #print(json.dumps(obj))



#    def test_loop(self):
#        print(self.event_status)
#
#        self.event_status.append(("cpu", 5))
#        while self.usage['cpu'] < 100:
#            self.fluctuate_usage()
#            self.deal_with_ongoing_events()
#            self.print_usage()
#            time.sleep(3)


def get_machines():
    with open("machine_list.json", "r") as f:
        json_machineList = json.load(f)
    machineList = []
    # TODO: Make it so that IDs are retrieved from the JSON file, and not calculated.
    id = 16
    # Ambient temperature, can be changed according to place later (if time)
    base_temp = 20
    for machine in json_machineList:
        machineList.append(Machine({"CPU": machine["cpu"], "GPU": machine['gpu'], "RAM": machine['ram'], "Disk": machine['disk'],
                           "OS": machine['os'], "Name": machine['name']}, id, machine['location'], base_temp))
        id += 1
    return machineList


def main():
    machineList = get_machines()
    while True:
        for machine in machineList:
            machine.machine_loop()
            #print(f"machine {machine.id} status {machine.status}")
            #machine.print_usage()
            #if machine.status == 1:
            #    machine.print_usage()
            if len(sys.argv)>1 and sys.argv[1]=='test':
                machine.print_usage()
            else:
                channel.basic_publish(exchange='machine-usage-exchange',
                          routing_key='routing.key',
                          body=machine.export_data())
            #print("sent machine")
            #print(users)
        time.sleep(1)

    # For testing purposes
    # machineList[0].test_loop()


if __name__ == "__main__":
    main()

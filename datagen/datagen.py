import numpy as np
import random as rm
import json
import time
import pika


connection = pika.BlockingConnection(pika.ConnectionParameters('localhost'))
channel = connection.channel()
channel.queue_declare(queue='datagen')


with open("programlist.json") as f:
    program_list = json.load(f)


class program():
    def __init__(self, name, id, type):
        self.name = name
        self.id = id
        self.type = type

class Machine():
    def __init__(self, specs, id, location, base_temp, name):
        # Internal Status Data
        self.status = 0  # 0 -> Off 1 ->On 2 -> Unavailable (temporarily)
        # [("cpu",+0.4)], for instance, will make cpu usage go up 0.4(%) by cycle
        self.event_status = []

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
        self.usage['temp'] = base_temp + rm.random()*3
        self.programs = []

    def crash(self):

            self.usage = {}
            self.usage["cpu"] = 0
            self.usage["gpu"] = 0
            self.usage["ram"] = 0
            self.usage["disk"] = 0
            self.usage["network_down"] = 0
            self.usage["network_up"] = 0
            self.usage['temp'] = 20 + rm.random()*3
            self.programs = []
            self.current_user = None
            self.event_status = []

            self.status=2 # Crash

    def event(self):
        pass
        rng = rm.random()
        alert_p = 0.7
        sus_p = 0.1
        crash_p = 0.2
        possible_events = ['cpu', 'gpu', 'ram', 'temp', 'disk']
        if rng < alert_p:
            self.event_status.append(
                (possible_events[rm.randint(0, len(possible_events)-1)], rm.random()*30))
        if rng<alert_p+sus_p:
            pass
            #TODO: implement sus events
        else:
            self.crash()
    def turn_on(self):
        if self.status:
            # already on
            pass
        else:
            self.status = 1

    def turn_off(self):
        if self.status:
            self.status = 0

    def close_program(self):
        if len(self.programs) < 2:
            self.turn_off()
        else:
            self.programs.pop(0)  # close last running

    def open_program(self):
        if len(self.programs) > 4:  # Too many programs running, replace event with close
            self.close_program()
        else:
            used_types = [prog["type"] for prog in self.programs]
            print(used_types)
            valid_prog=[]
            add=True
            for prog in program_list:
                add=True
                for t in used_types:
                    #print(f"is {prog['type']} the same as {t}???? {'true' if prog['type']==t  else 'False'}")
                    if prog["type"]==t:
                        add=False
                        break
                if add:
                    valid_prog.append(prog)

            #valid_programs = [prog for prog in program_list if (
            #    (prog["type"] == ptype or ptype == None) and  not (prog["type"]  in used_types))]
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
            pass 
            # Delete some randomly (30% chance)
            self.event_status = [
                ev for ev in self.event_status if rm.random() < 0.8]
            for ev in self.event_status:
                print(f"test: {ev}")
                self.usage[ev[0]] += ev[1]
        else:
            return

    def fluctuate_usage(self):
        for spec in self.usage:
            rng = (rm.random()*10-5)+np.log(len(self.programs) * \
                rm.random()*3)  # [-5,5]
            self.usage[spec] += rng
            self.usage[spec] = round(self.usage[spec], 2)
            if self.usage[spec] < 0:
                self.usage[spec] = 0
            if self.usage[spec] > 100:
                if spec == "network_down" or spec == "network_up":
                    pass
                elif spec == "temp":
                    self.turn_off()
                    self.status=2
                else:
                    self.usage[spec] = 100


    def machine_loop(self):
        if not self.status:
            self.machine_off_loop()
        elif self.status ==2:
            rng=rm.random()
            if rng<0.5:         # Can become available or stay poofed
                self.status=0
        else:
            # Take care of ongoing events

            self.deal_with_ongoing_events()
            self.fluctuate_usage()
            self.print_usage()
            
            rng = rm.random()
            pass_p = 0.75
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

        cpu: {self.usage['cpu']}%
        gpu: {self.usage['gpu']}%
        ram: {self.usage['ram']}%
        temp: {self.usage['temp']}ºC
        disk: {self.usage['disk']}%
        network (up): {self.usage['network_up']} MB/s
        network (down) {self.usage['network_down']} MB/s
        running: {self.programs}

        ongoing events: {self.event_status}
        """)


    def export_data(self):
        obj = {
            'usage':{
                'cpu':self.usage['cpu'],
                'gpu':self.usage['gpu'],
                'ram':self.usage['ram'],
                'disk':self.usage['disk'],
                'network_up':self.usage['network_up'],
                'network_down':self.usage['network_down'],
                'temp':round(self.usage['temp'],2),
                'programs':self.programs,
                'status':self.status
            }
        }
        
        return obj
        #channel.basic_publish(exchange='',
        #              routing_key='datagen',
        #              body=json.dumps(obj))
        #print(json.dumps(obj))



    def test_loop(self):
        print(self.event_status)

        self.event_status.append(("cpu", 5))
        while self.usage['cpu'] < 100:
            self.fluctuate_usage()
            self.deal_with_ongoing_events()
            self.print_usage()
            time.sleep(3)


def get_machines():
    with open("test_machinelist.json", "r") as f:
        json_machineList = json.load(f)
    machineList = []
    id = 0
    # Ambient temperature, can be changed according to place later (if time)
    base_temp = 20
    for machine in json_machineList:
        machineList.append(Machine({"CPU": machine["cpu"], "GPU": machine['gpu'], "RAM": machine['ram'], "Disk": machine['disk'],
                           "OS": machine['os'], "Name": machine['name']}, id, machine['location'], base_temp, machine['name']))
        id += 1
    return machineList


def main():
    machineList = get_machines()
    while True:
        for machine in machineList:
            machine.machine_loop()
            machine.export_data()
            channel.basic_publish(exchange='',
                      routing_key='datagen',
                      body=machine.export_data())
        
        time.sleep(3)

    # For testing purposes
    # machineList[0].test_loop()


main()

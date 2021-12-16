import numpy as np
import random as rm
import json
from MarkovChain import MarkovChain

with open("programlist.json") as f:
    program_list=json.load(f)


class program():
    def __init__(self, name, id, type):
        self.name = name
        self.id = id
        self.type = type


class Machine():
    def __init__(self, specs, id, location, base_temp, name):
        # Internal Status Data
        self.status = False

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
        self.coords = (None,None)# (x,y)
        self.current_user = None
        self.usage = {}
        self.usage["CPU"] = 0
        self.usage["GPU"] = 0
        self.usage["RAM"] = 0
        self.usage["Disk"] = 0
        self.usage["OS"] = 0
        self.usage["Name"] = name
        # Can be slightly above room temp
        self.cpu_temp = base_temp + rm.random()*3
        self.gpu_temp = base_temp + rm.random()*3
        self.programs = []


    def turn_on(self):
        pass
        if self.status:
            #already on
            pass
        else:
            self.status=True

    def turn_off(self):
        if self.status:
            self.status=False
        else:
            pass

    def open_program(self,ptype=None):
        rng=rm.randint(0,len([prog for prog in program_list if (prog["type"]==ptype or ptype==None)]))
        self.programs.append(program_list[rng])

    def machine_off_loop(self):
        rng=rm.random()
        if rng<0.2:
            self.turn_on()
            self.open_program()

    def machine_loop(self):
        if not self.status:
            self.machine_off_loop()
        else:
            pass
            #TODO: Implement here the main 'on' loop.

            
def get_machines():
    with open("machinelist.json","r") as f:
        json_machineList=json.load(f)
    machineList=[]
    id=0
    base_temp=20 # Ambient temperature, can be changed according to place later (if time)
    for machine in json_machineList:
        machineList.append(Machine({"CPU":machine["cpu"],"GPU":machine['gpu'],"RAM":machine['ram'],"Disk":machine['disk'],"OS":machine['os'],"Name":machine['name']},id,machine['location'],base_temp,machine['name']))
        id+=1
    return machineList
    



def main():
    machineList=get_machines()
    for machine in machineList:
        machine.machine_loop


main()
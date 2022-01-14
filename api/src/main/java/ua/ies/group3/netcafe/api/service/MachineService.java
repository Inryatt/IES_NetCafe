package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.*;
import ua.ies.group3.netcafe.api.repository.MachineRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SoftwareService softwareService;

    public Machine saveMachine(Machine machine) {
        return machineRepository.save(machine);
    }

    public List<Machine> saveMachines(List<Machine> machines) {
        return machineRepository.saveAll(machines);
    }

    public Optional<Machine> findMachineById(long id) {
        return machineRepository.findById(id);
    }

    public List<Machine> findAllMachines() {
        return machineRepository.findAll();
    }

    public List<Machine> findMachinesByLocation(Location location) {
        return machineRepository.findMachinesByLocation(location);
    }

    public Machine updateMachine(MachineUsage usage) {
        Optional<Machine> machineOptional = findMachineById(usage.getMachineId());
        if (machineOptional.isPresent()) {
            Machine machine = machineOptional.get();
            Optional<User> userOptional = userService.findUserById(usage.getUserId());
            if (userOptional.isPresent()) {
                User user = userOptional.get();
                machine.setCurrentUser(user);
                List<Integer> softwareIds = usage.getSoftwareUsage();
                List<Software> softwares = new ArrayList<>();
                for (Integer softwareId : softwareIds) {
                    Optional<Software> softwareOptional = softwareService.findSoftwareById(softwareId);
                    softwareOptional.ifPresent(softwares::add);
                }
                machine.setSoftwares(softwares);
                machine.setTimestamp(usage.getTimestampStart());
                machine.setCpuUsage(usage.getCpuUsage());
                machine.setGpuUsage(usage.getGpuUsage());
                machine.setDiskUsage(usage.getDiskUsage());
                machine.setRamUsage(usage.getRamUsage());
                machine.setNetworkDownUsage(usage.getNetworkDownUsage());
                machine.setNetworkUpUsage(usage.getNetworkUpUsage());
                machine.setPowerUsage(usage.getPowerUsage());
                machine.setCpuTemp(usage.getCpuTemp());
                machine.setGpuTemp(usage.getGpuTemp());
                machine.setStatus(usage.getUserId() == 0 ? 0 : 1);
                machineRepository.save(machine);
                return machine;
            }
            machine.setCurrentUser(null);
            machine.setSoftwares(new ArrayList<>());
            machine.setTimestamp(usage.getTimestampStart());
            machine.setCpuUsage(0);
            machine.setGpuUsage(0);
            machine.setDiskUsage(0);
            machine.setRamUsage(0);
            machine.setNetworkDownUsage(0);
            machine.setNetworkUpUsage(0);
            machine.setPowerUsage(0);
            machine.setCpuTemp(0);
            machine.setGpuTemp(0);
            machine.setStatus(0);
            machineRepository.save(machine);
            return machine;
        }
        return null;
    }
}

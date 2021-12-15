package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.Location;
import ua.ies.group3.netcafe.api.model.Machine;
import ua.ies.group3.netcafe.api.repository.MachineRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MachineService {
    @Autowired
    private MachineRepository machineRepository;

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
}

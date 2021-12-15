package ua.ies.group3.netcafe.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.ies.group3.netcafe.api.model.Location;
import ua.ies.group3.netcafe.api.model.Machine;
import ua.ies.group3.netcafe.api.model.Software;
import ua.ies.group3.netcafe.api.model.User;
import ua.ies.group3.netcafe.api.service.LocationService;
import ua.ies.group3.netcafe.api.service.MachineService;
import ua.ies.group3.netcafe.api.service.SoftwareService;
import ua.ies.group3.netcafe.api.service.UserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Controller {
    @Autowired
    private LocationService locationService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private SoftwareService softwareService;

    @Autowired
    private UserService userService;


    // Location

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.findAllLocations();
    }

    @PostMapping("/locations")
    public Location addLocation(@Valid @RequestBody Location location) {
        return locationService.saveLocation(location);
    }


    // Machine

    @GetMapping("/machines")
    public List<Machine> findAllMachines() {
        return machineService.findAllMachines();
    }

    @PostMapping("/machines")
    public Machine addMachine(@Valid @RequestBody Machine machine) {
        return machineService.saveMachine(machine);
    }
    

    @GetMapping("/locations/{id}/machines")
    public List<Machine> findLocationMachines(@PathVariable(value = "id") Long id) {
        Optional<Location> locationOptional = locationService.findLocationById(id);
        if (locationOptional.isPresent()) {
            Location location = locationOptional.get();
            return machineService.findMachinesByLocation(location);
        }
        return new ArrayList<>();
    }


    // Software

    @GetMapping("/softwares")
    public List<Software> findAllSoftwares() {
        return softwareService.findAllSoftwares();
    }

    @PostMapping("/softwares")
    public Software addSoftware(Software software) {
        return softwareService.saveSoftware(software);
    }


    // User

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }
}

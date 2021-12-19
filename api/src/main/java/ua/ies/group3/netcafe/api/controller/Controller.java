package ua.ies.group3.netcafe.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ies.group3.netcafe.api.exception.ResourceNotFoundException;
import ua.ies.group3.netcafe.api.model.Location;
import ua.ies.group3.netcafe.api.model.Machine;
import ua.ies.group3.netcafe.api.model.Software;
import ua.ies.group3.netcafe.api.model.User;
import ua.ies.group3.netcafe.api.service.LocationService;
import ua.ies.group3.netcafe.api.service.MachineService;
import ua.ies.group3.netcafe.api.service.SoftwareService;
import ua.ies.group3.netcafe.api.service.UserService;

import javax.validation.Valid;
import java.util.List;

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

    private final ObjectMapper objectMapper = new ObjectMapper();

    // Location

    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.findAllLocations();
    }

    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> findLocationById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Location location = locationService.findLocationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id: " + id));
        return ResponseEntity.ok(location);
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

    @GetMapping("/machines/{id}")
    public ResponseEntity<Machine> findMachineById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Machine machine = machineService.findMachineById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id: " + id));
        return ResponseEntity.ok(machine);
    }


    @PostMapping("/machines")
    public Machine addMachine(@Valid @RequestBody Machine machine) {
        return machineService.saveMachine(machine);
    }

    @PatchMapping(path = "/machines/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody JsonPatch patch) throws ResourceNotFoundException {
        Machine machine = machineService.findMachineById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id: " + id));
        try {
            Machine machinePatched = applyPatchToMachine(patch, machine);
            machineService.saveMachine(machinePatched);
            return ResponseEntity.ok(machinePatched);
        } catch (JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Machine applyPatchToMachine(JsonPatch patch, Machine targetMachine) throws JsonPatchException, JsonProcessingException {
        JsonNode patched = patch.apply(objectMapper.convertValue(targetMachine, JsonNode.class));
        return objectMapper.treeToValue(patched, Machine.class);
    }


    @GetMapping("/locations/{id}/machines")
    public List<Machine> findLocationMachines(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Location location = locationService.findLocationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id: " + id));
        return machineService.findMachinesByLocation(location);
    }


    // Software

    @GetMapping("/softwares")
    public List<Software> findAllSoftwares() {
        return softwareService.findAllSoftwares();
    }

    @GetMapping("/softwares/{id}")
    public ResponseEntity<Software> findSoftwareById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Software software = softwareService.findSoftwareById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Software not found for this id: " + id));
        return ResponseEntity.ok(software);
    }

    @PostMapping("/softwares")
    public Software addSoftware(@Valid @RequestBody Software software) {
        return softwareService.saveSoftware(software);
    }


    // User

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
        return ResponseEntity.ok(user);
    }

    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }
}

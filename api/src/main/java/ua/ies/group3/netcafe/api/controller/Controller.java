package ua.ies.group3.netcafe.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ies.group3.netcafe.api.exception.ResourceNotFoundException;
import ua.ies.group3.netcafe.api.model.*;
import ua.ies.group3.netcafe.api.repository.MachineUsageRepository;
import ua.ies.group3.netcafe.api.service.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
//@CrossOrigin(origins = "*")// "http://frontend:3000")
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

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MachineUsageService machineUsageService;

    @Autowired
    private AlarmService alarmService;

    // Location

    @Operation(summary = "Get all locations.")
    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.findAllLocations();
    }

    @Operation(summary = "Get a location by ID.")
    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> findLocationById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Location location = locationService.findLocationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id: " + id));
        return ResponseEntity.ok(location);
    }

    @Operation(summary = "Add a location.")
    @PostMapping("/locations")
    public Location addLocation(@Valid @RequestBody Location location) {
        return locationService.saveLocation(location);
    }


    // Machine

    @Operation(summary = "Get all machines.")
    @GetMapping("/machines")
    public List<Machine> findAllMachines() {
        return machineService.findAllMachines();
    }

    @Operation(summary = "Get a machine by ID.")
    @GetMapping("/machines/{id}")
    public ResponseEntity<Machine> findMachineById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Machine machine = machineService.findMachineById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id: " + id));
        return ResponseEntity.ok(machine);
    }


    @Operation(summary = "Add a machine.")
    @PostMapping("/machines")
    public Machine addMachine(@Valid @RequestBody Machine machine) {
        return machineService.saveMachine(machine);
    }


    @Operation(summary = "Get the machines of a location through its ID.")
    @GetMapping("/locations/{id}/machines")
    public List<Machine> findLocationMachines(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Location location = locationService.findLocationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id: " + id));
        return machineService.findMachinesByLocation(location);
    }


    // Software

    @Operation(summary = "Get all software.")
    @GetMapping("/softwares")
    public List<Software> findAllSoftwares() {
        return softwareService.findAllSoftwares();
    }

    @Operation(summary = "Get a software by ID.")
    @GetMapping("/softwares/{id}")
    public ResponseEntity<Software> findSoftwareById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Software software = softwareService.findSoftwareById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Software not found for this id: " + id));
        return ResponseEntity.ok(software);
    }

    @Operation(summary = "Add a software.")
    @PostMapping("/softwares")
    public Software addSoftware(@Valid @RequestBody Software software) {
        return softwareService.saveSoftware(software);
    }


    // User

    @Operation(summary = "Get all users.")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @Operation(summary = "Get a user by ID.")
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Add a user.")
    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }


    // Session

    @Operation(summary = "Get sessions matching the filters.")
    @GetMapping("/sessions")
    public List<Session> findSessions(@RequestParam(name = "machine", required = false) Long machineId,
                                      @RequestParam(name = "user", required = false) Long userId) {
        // System.out.println("machineId: " + machineId + ", userId: " + userId);
        if (machineId != null && userId != null)
            return sessionService.findSessionsByMachineIdAndUserId(machineId, userId);
        else if (machineId != null)
            return sessionService.findSessionsByMachineId(machineId);
        else if (userId != null)
            return sessionService.findSessionsByUserId(userId);
        else
            return sessionService.findSessions();
    }


    // Machine Usage

    @Operation(summary = "Get machine usages matching the filters.")
    @GetMapping("/usages")
    public List<MachineUsage> findUsages(@RequestParam(name = "machine", required = false) Long machineId,
                                         @RequestParam(name = "ts-start", required = false) Long tsStart,
                                         @RequestParam(name = "ts-end", required = false) Long tsEnd) {
        if (tsStart == null)
            tsStart = -Long.MAX_VALUE;
        if (tsEnd == null)
            tsEnd = Long.MAX_VALUE;
        if (machineId == null)
            return machineUsageService.findMachineUsageByTimestampStartBetween(tsStart, tsEnd);
        return machineUsageService.findMachineUsageByMachineIdAndTimestampStartBetween(machineId, tsStart, tsEnd);
    }


    // Alarms

    @Operation(summary = "Get alarms matching the filters.")
    @GetMapping("/alarms")
    public List<Alarm> getAlarms(@RequestParam(name = "machine", required = false) Long machineId,
                                 @RequestParam(name = "ts-start", required = false) Long tsStart,
                                 @RequestParam(name = "ts-end", required = false) Long tsEnd) {
        if (tsStart == null)
            tsStart = -Long.MAX_VALUE;
        if (tsEnd == null)
            tsEnd = Long.MAX_VALUE;
        if (machineId == null)
            return alarmService.findAlarmsByTimestampBetween(tsStart, tsEnd);
        return alarmService.findAlarmsByMachineIdAndTimestampBetween(machineId, tsStart, tsEnd);
    }

    @Operation(summary = "Update the seen status of an alarm.")
    @PostMapping("/alarms")
    public Alarm setAlarmSeen(@Valid @RequestBody AlarmSeen alarmSeen) {
        return alarmService.setAlarmSeen(alarmSeen.getId(), alarmSeen.isSeen());
    }

    // test session gets
//    @PostMapping("/test-sessions")
//    public Session addSession(@Valid @RequestBody Session session) {
//        return sessionService.saveSession(session);
//    }
//
//    @PostMapping("/test-usages")
//    public MachineUsage addMachineUsage(@Valid @RequestBody MachineUsage machineUsage) {
//        return machineUsageService.saveMachineUsage(machineUsage);
//    }

//    @PatchMapping(path = "/machines/{id}", consumes = "application/json-patch+json")
//    public ResponseEntity<Machine> updateMachine(@PathVariable Long id, @RequestBody JsonPatch patch) throws ResourceNotFoundException {
//        Machine machine = machineService.findMachineById(id)
//                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id: " + id));
//        try {
//            Machine machinePatched = applyPatchToMachine(patch, machine);
//            machineService.saveMachine(machinePatched);
//            return ResponseEntity.ok(machinePatched);
//        } catch (JsonPatchException | JsonProcessingException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }

//    private Machine applyPatchToMachine(JsonPatch patch, Machine targetMachine) throws JsonPatchException, JsonProcessingException {
//        JsonNode patched = patch.apply(objectMapper.convertValue(targetMachine, JsonNode.class));
//        return objectMapper.treeToValue(patched, Machine.class);
//    }
}
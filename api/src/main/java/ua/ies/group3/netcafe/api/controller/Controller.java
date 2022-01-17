package ua.ies.group3.netcafe.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.ies.group3.netcafe.api.auxiliarymodel.AlarmSeen;
import ua.ies.group3.netcafe.api.exception.ResourceNotFoundException;
import ua.ies.group3.netcafe.api.model.*;
import ua.ies.group3.netcafe.api.service.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@CrossOrigin(origins = "*")// "http://frontend:3000")
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
    @ApiResponse(responseCode = "200", description = "Got all locations.")
    @GetMapping("/locations")
    public List<Location> getAllLocations() {
        return locationService.findAllLocations();
    }

    @Operation(summary = "Get a location by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Location found."),
            @ApiResponse(responseCode = "404", description = "Location not found.")
    })
    @GetMapping("/locations/{id}")
    public ResponseEntity<Location> findLocationById(
            @Parameter(description = "ID of the location to be searched.") @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Location location = locationService.findLocationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id: " + id));
        return ResponseEntity.ok(location);
    }

    @Operation(summary = "Add a location.")
    @ApiResponse(responseCode = "200", description = "Location added.")
    @PostMapping("/locations")
    public Location addLocation(@Valid @RequestBody Location location) {
        return locationService.saveLocation(location);
    }


    // Machine

    @Operation(summary = "Get all machines.")
    @ApiResponse(responseCode = "200", description = "Got all machines.")
    @GetMapping("/machines")
    public List<Machine> findAllMachines() {
        return machineService.findAllMachines();
    }

    @Operation(summary = "Get a machine by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Machine found."),
            @ApiResponse(responseCode = "404", description = "Machine not found.")
    })
    @GetMapping("/machines/{id}")
    public ResponseEntity<Machine> findMachineById(
            @Parameter(description = "ID of the machine to be searched.") @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Machine machine = machineService.findMachineById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Machine not found for this id: " + id));
        return ResponseEntity.ok(machine);
    }


    @Operation(summary = "Add a machine.")
    @ApiResponse(responseCode = "200", description = "Machine added.")
    @PostMapping("/machines")
    public Machine addMachine(@Valid @RequestBody Machine machine) {
        return machineService.saveMachine(machine);
    }


    @Operation(summary = "Get the machines of a location through its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Got all machines of the specified location."),
            @ApiResponse(responseCode = "404", description = "Location not found.")
    })
    @GetMapping("/locations/{id}/machines")
    public List<Machine> findLocationMachines(
            @Parameter(description = "ID of the location whose machines are to be found.")
            @PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Location location = locationService.findLocationById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Location not found for this id: " + id));
        return machineService.findMachinesByLocation(location);
    }


    // Software

    @Operation(summary = "Get all software.")
    @ApiResponse(responseCode = "200", description = "Got all software.")
    @GetMapping("/softwares")
    public List<Software> findAllSoftwares() {
        return softwareService.findAllSoftwares();
    }

    @Operation(summary = "Get a software by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Software found."),
            @ApiResponse(responseCode = "404", description = "Software not found.")
    })
    @GetMapping("/softwares/{id}")
    public ResponseEntity<Software> findSoftwareById(
            @Parameter(description = "ID of the software to be searched.") @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        Software software = softwareService.findSoftwareById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Software not found for this id: " + id));
        return ResponseEntity.ok(software);
    }

    @Operation(summary = "Add a software.")
    @ApiResponse(responseCode = "200", description = "Software added.")
    @PostMapping("/softwares")
    public Software addSoftware(@Valid @RequestBody Software software) {
        return softwareService.saveSoftware(software);
    }


    // User

    @Operation(summary = "Get all users.")
    @ApiResponse(responseCode = "200", description = "Got all users.")
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.findAllUsers();
    }

    @Operation(summary = "Get a user by ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found."),
            @ApiResponse(responseCode = "404", description = "User not found.")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<User> findUserById(
            @Parameter(description = "ID of the user to be searched.") @PathVariable(value = "id") Long id)
            throws ResourceNotFoundException {
        User user = userService.findUserById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found for this id: " + id));
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Add a user.")
    @ApiResponse(responseCode = "200", description = "User added.")
    @PostMapping("/users")
    public User addUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }


    // Session

    @Operation(summary = "Get sessions matching the filters.")
    @ApiResponse(responseCode = "200", description = "Sessions found.")
    @GetMapping("/sessions")
    public List<Session> findSessions(
            @Parameter(description = "Machine ID filter") @RequestParam(name = "machine", required = false) Long machineId,
            @Parameter(description = "User ID filter") @RequestParam(name = "user", required = false) Long userId) {
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
    @ApiResponse(responseCode = "200", description = "Machine usages found.")
    @GetMapping("/usages")
    public List<MachineUsage> findUsages(
            @Parameter(description = "Machine ID filter") @RequestParam(name = "machine", required = false) Long machineId,
            @Parameter(description = "Start timestamp filter") @RequestParam(name = "ts-start", required = false) Long tsStart,
            @Parameter(description = "End timestamp filter") @RequestParam(name = "ts-end", required = false) Long tsEnd) {
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
    @ApiResponse(responseCode = "200", description = "Alarms found.")
    @GetMapping("/alarms")
    public ResponseEntity<List<Alarm>> getAlarms(
            @Parameter(description = "Machine ID filter") @RequestParam(name = "machine", required = false) Long machineId,
            @Parameter(description = "Start timestamp filter") @RequestParam(name = "ts-start", required = false) Long tsStart,
            @Parameter(description = "End timestamp filter") @RequestParam(name = "ts-end", required = false) Long tsEnd) {
        if (tsStart == null)
            tsStart = -Long.MAX_VALUE;
        if (tsEnd == null)
            tsEnd = Long.MAX_VALUE;
        if (machineId == null)
            return ResponseEntity.ok(alarmService.findAlarmsByTimestampBetween(tsStart, tsEnd));
        return ResponseEntity.ok(alarmService.findAlarmsByMachineIdAndTimestampBetween(machineId, tsStart, tsEnd));
    }

    @Operation(summary = "Update the seen status of an alarm.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alarm status updated."),
            @ApiResponse(responseCode = "404", description = "No alarm with specified ID found.")
    })
    @PostMapping("/alarms")
    public Alarm setAlarmSeen(@Valid @RequestBody AlarmSeen alarmSeen) throws ResourceNotFoundException {
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
}

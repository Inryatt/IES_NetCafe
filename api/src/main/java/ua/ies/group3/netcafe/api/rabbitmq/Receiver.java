package ua.ies.group3.netcafe.api.rabbitmq;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import ua.ies.group3.netcafe.api.enums.AlarmTypes;
import ua.ies.group3.netcafe.api.model.Alarm;
import ua.ies.group3.netcafe.api.model.Location;
import ua.ies.group3.netcafe.api.model.MachineUsage;
import ua.ies.group3.netcafe.api.service.*;

import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@Component
public class Receiver {
    private CountDownLatch latch = new CountDownLatch(1);

    @Autowired
    private MachineUsageService machineUsageService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private MachineService machineService;

    @Autowired
    private AlarmService alarmService;

    @Autowired
    private SoftwareService softwareService;

    @Autowired
    private LocationService locationService;

    public void receiveMessage(byte[] message) {
        String msg = new String(message, StandardCharsets.UTF_8);
        Gson g = new Gson();
        
	MachineUsage machineUsage = g.fromJson(msg, MachineUsage.class);
	Location base_location = g.fromJson(msg,Location.class);
	
        System.out.println("TEMPERATURAAAAAAAAAAAAAAAA: "+base_location.getTemperature());
	
        System.out.println("NOT TEMPERATURAAAAAAAAAAAAAAAA: "+machineUsage.getMachineId());
	//Message mesg = g.fromJson(msg, Message.class);
	//
	
	//System.out.println(Double.valueOf(base_location.getTemperature()));
        
	if (base_location.getTemperature() ==null) {
            //MachineUsage machineUsage = mesg.generateMachine();
            
	    System.out.println("Received Machine Usage\n" + machineUsage);
            machineUsageService.saveMachineUsage(machineUsage); // MongoDB MachineUsage
            sessionService.updateSession(machineUsage);         // MongoDB Session
            try {
                System.out.println("MySQL saving machine\n" + machineService.updateMachine(machineUsage)); // MySQL Machine
            } catch (DataIntegrityViolationException ignored) {
            }
            createAlarmIfNeeded(machineUsage);
            latch.countDown();
	    System.out.println("Received machine usage");
	    return;
        }


	
        if (machineUsage.getMachineId()==0) {

            List<Location> locations = locationService.findAllLocations();
            for (Location location : locations) {
                location.setHumidity(base_location.getHumidity());
                location.setTemperature(base_location.getTemperature());
                locationService.saveLocation(location);
            	System.out.println("Location updated:"+location);
	    }
	    System.out.println("Locations updated");
        	return;

	}
        
            System.out.println("Message not identified.");
        
    }

    private final double MAX_CPU_TEMP = 90;
    private final double MAX_GPU_TEMP = 90;

    public Alarm createAlarmIfNeeded(MachineUsage usage) {
        boolean saveAlarm = false;
        StringBuilder message = new StringBuilder();
        AlarmTypes type = AlarmTypes.INFO;
        if (usage.getGpuTemp() > MAX_CPU_TEMP) {
            message.append("CPU temperature above ").append(MAX_CPU_TEMP).append("??C.\n");
            if (type.getSeverity() < AlarmTypes.WARNING.getSeverity())
                type = AlarmTypes.WARNING;
            saveAlarm = true;
        }
        if (usage.getGpuTemp() > MAX_GPU_TEMP) {
            message.append("GPU temperature above ").append(MAX_GPU_TEMP).append("??C.\n");
            if (type.getSeverity() < AlarmTypes.WARNING.getSeverity())
                type = AlarmTypes.WARNING;
            saveAlarm = true;
        }
        // List of unidentified software (software whose ID isn't in the MySQL DB)
        List<Integer> unidentifiedSoftware = usage.getSoftwareUsage() == null ? new ArrayList<>()
                : usage.getSoftwareUsage().stream().filter(
                id -> !softwareService.softwareIsKnown(id)
        ).collect(Collectors.toList());
        if (!unidentifiedSoftware.isEmpty()) {
            message.append("Unidentified software running: [");
            unidentifiedSoftware.forEach(id -> message.append(id).append(","));
            message.setLength(message.length() - 1); // Remove last comma
            message.append("].\n");
            saveAlarm = true;
        }

        if (saveAlarm) {
            message.setLength(message.length() - 1); // Remove last newline
            return alarmService.saveAlarm(new Alarm(
                    usage.getMachineId(),
                    usage.getUserId(),
                    message.toString(),
                    type.toString(),
                    usage.getTimestamp()
            ));
        }
        return null;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}

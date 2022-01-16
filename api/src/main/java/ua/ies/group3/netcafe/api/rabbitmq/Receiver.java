package ua.ies.group3.netcafe.api.rabbitmq;

import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ies.group3.netcafe.api.enums.AlarmTypes;
import ua.ies.group3.netcafe.api.model.Alarm;
import ua.ies.group3.netcafe.api.model.MachineUsage;
import ua.ies.group3.netcafe.api.service.AlarmService;
import ua.ies.group3.netcafe.api.service.MachineService;
import ua.ies.group3.netcafe.api.service.MachineUsageService;
import ua.ies.group3.netcafe.api.service.SessionService;

import java.nio.charset.StandardCharsets;

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

    public void receiveMessage(byte[] message) {
        String msg = new String(message, StandardCharsets.UTF_8);
        Gson g = new Gson();
        MachineUsage machineUsage = g.fromJson(msg, MachineUsage.class);
        System.out.println("Received Machine Usage\n" + machineUsage);
        machineUsageService.saveMachineUsage(machineUsage); // MongoDB MachineUsage
        sessionService.updateSession(machineUsage);         // MongoDB Session

        System.out.println("Saving machine MySQL\n" + machineService.updateMachine(machineUsage));         // MySQL   Machine
        latch.countDown();
    }

    private final double MAX_CPU_TEMP = 100;
    private final double MAX_GPU_TEMP = 100;

    public Alarm createAlarmIfNeeded(MachineUsage usage) {
        boolean saveAlarm = false;
        StringBuilder message = new StringBuilder();
        AlarmTypes type = AlarmTypes.INFO;
        if (usage.getGpuTemp() > MAX_CPU_TEMP) {
            message.append("CPU temperature above ").append(MAX_CPU_TEMP).append("ºC.");
            if (type.getSeverity() < AlarmTypes.WARNING.getSeverity())
                type = AlarmTypes.WARNING;
            saveAlarm = true;
        }
        if (usage.getGpuTemp() > MAX_GPU_TEMP) {
            message.append("GPU temperature above ").append(MAX_GPU_TEMP).append("ºC.");
            if (type.getSeverity() < AlarmTypes.WARNING.getSeverity())
                type = AlarmTypes.WARNING;
            saveAlarm = true;
        }
        if (saveAlarm) {
            Alarm alarm = new Alarm(
                    usage.getMachineId(),
                    usage.getUserId(),
                    message.toString(),
                    false,
                    type.toString(),
                    usage.getTimestampStart()
            );
            alarmService.saveAlarm(alarm);
        }
        return null;
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
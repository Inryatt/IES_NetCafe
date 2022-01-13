package ua.ies.group3.netcafe.api.rabbitmq;

import java.util.concurrent.CountDownLatch;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.ies.group3.netcafe.api.model.MachineUsage;
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

    public void receiveMessage(byte[] message) {
        String msg = new String(message, StandardCharsets.UTF_8);
        Gson g = new Gson();
        // Teste s = g.fromJson(msg, Teste.class);
        MachineUsage machineUsage = g.fromJson(msg, MachineUsage.class);
//        machineUsage.setId(Long.toString(sequenceGeneratorService.generateSequence(MachineUsage.SEQUENCE_NAME)));
        System.out.println("Received Machine Usage\n" + machineUsage);
        machineUsageService.saveMachineUsage(machineUsage);

        sessionService.updateSession(machineUsage);

        latch.countDown();
    }

    public CountDownLatch getLatch() {
        return latch;
    }
}
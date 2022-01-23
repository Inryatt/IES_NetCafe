package ua.ies.group3.netcafe.api.rabbitmq;

import java.util.List;

import ua.ies.group3.netcafe.api.model.MachineUsage;

public class Message {

    private long machineId;
    private long userId;
    private long timestamp;
    private double cpuUsage;
    private double gpuUsage;
    private double networkUpUsage;
    private double networkDownUsage;
    private double powerUsage;
    private double diskUsage;
    private double ramUsage;
    private int uptime;
    private List<Integer> softwareUsage;
    private double cpuTemp;
    private double gpuTemp;
    private boolean isMachine = false;

    // -------------------------

    private double temperature;
    private double humidity;
    private boolean isLocation = false;

    // -------------------------

    public Message(long machineId, long userId, long timestamp, double cpuUsage,
                        double gpuUsage, double networkUpUsage, double networkDownUsage, double powerUsage,
                        double diskUsage, double ramUsage, int uptime, List<Integer> softwareUsage,
                        double cpuTemp, double gpuTemp) {
        this.machineId = machineId;
        this.userId = userId;
        this.timestamp = timestamp;
        this.cpuUsage = cpuUsage;
        this.gpuUsage = gpuUsage;
        this.networkUpUsage = networkUpUsage;
        this.networkDownUsage = networkDownUsage;
        this.powerUsage = powerUsage;
        this.diskUsage = diskUsage;
        this.ramUsage = ramUsage;
        this.uptime = uptime;
        this.softwareUsage = softwareUsage;
        this.cpuTemp = cpuTemp;
        this.gpuTemp = gpuTemp;
        this.isMachine = true;
    }

    public Message(double temperature, double humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.isLocation = true;
    }

    public boolean isMachine() {
        return isMachine;
    }

    public boolean isLocation() {
        return isLocation;
    }

    public MachineUsage generateMachine() {
        return new MachineUsage(machineId, userId, timestamp, cpuUsage,
                        gpuUsage, networkUpUsage, networkDownUsage, powerUsage,
                        diskUsage, ramUsage, uptime, softwareUsage,
                        cpuTemp, gpuTemp);
    }

    public double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return this.humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }


}

package model;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MachineUsage {
    private Machine machine;
    private User user;
    private LocalDate timestamp;
    private double cpuUsage;
    private double gpuUsage;
    private double diskUsage;
    private double memUsage;
    private double cpuTemp;
    private double gpuTemp;
    private double networkUsage;
    private double powerUsage;
    private int uptime;
    private List<Software> softwareUsage;

    public MachineUsage(Machine machine, User user, LocalDate timestamp, double cpuUsage, double gpuUsage, double diskUsage, double memUsage, double cpuTemp, double gpuTemp, double networkUsage, double powerUsage, int uptime, List<Software> softwareUsage) {
        this.machine = machine;
        this.user = user;
        this.timestamp = timestamp;
        this.cpuUsage = cpuUsage;
        this.gpuUsage = gpuUsage;
        this.diskUsage = diskUsage;
        this.memUsage = memUsage;
        this.cpuTemp = cpuTemp;
        this.gpuTemp = gpuTemp;
        this.networkUsage = networkUsage;
        this.powerUsage = powerUsage;
        this.uptime = uptime;
        this.softwareUsage = softwareUsage;
    }

    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDate timestamp) {
        this.timestamp = timestamp;
    }

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        this.cpuUsage = cpuUsage;
    }

    public double getGpuUsage() {
        return gpuUsage;
    }

    public void setGpuUsage(double gpuUsage) {
        this.gpuUsage = gpuUsage;
    }

    public double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }

    public double getMemUsage() {
        return memUsage;
    }

    public void setMemUsage(double memUsage) {
        this.memUsage = memUsage;
    }

    public double getCpuTemp() {
        return cpuTemp;
    }

    public void setCpuTemp(double cpuTemp) {
        this.cpuTemp = cpuTemp;
    }

    public double getGpuTemp() {
        return gpuTemp;
    }

    public void setGpuTemp(double gpuTemp) {
        this.gpuTemp = gpuTemp;
    }

    public double getNetworkUsage() {
        return networkUsage;
    }

    public void setNetworkUsage(double networkUsage) {
        this.networkUsage = networkUsage;
    }

    public double getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(double powerUsage) {
        this.powerUsage = powerUsage;
    }

    public int getUptime() {
        return uptime;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }

    public List<Software> getSoftwareUsage() {
        return softwareUsage;
    }

    public void setSoftwareUsage(List<Software> softwareUsage) {
        this.softwareUsage = softwareUsage;
    }
}

package ua.ies.group3.netcafe.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document("machineUsages")
public class MachineUsage {
    @Id
    private String id;

    private long machineId;
    private long userId;
    private long timestampStart;
    private double cpuUsage;
    private double gpuUsage;
    private double networkUpUsage;
    private double networkDownUsage;
    private double powerUsage;
    private double diskUsage;
    private double ramUsage;
    private int uptime;
    private List<Integer> softwareUsage;

    public MachineUsage(long machineId, long userId, long timestampStart, double cpuUsage,
                        double gpuUsage, double networkUpUsage, double networkDownUsage, double powerUsage,
                        double diskUsage, double ramUsage, int uptime, List<Integer> softwareUsage) {
        // this.id = id;
        this.machineId = machineId;
        this.userId = userId;
        this.timestampStart = timestampStart;
        this.cpuUsage = cpuUsage;
        this.gpuUsage = gpuUsage;
        this.networkUpUsage = networkUpUsage;
        this.networkDownUsage = networkDownUsage;
        this.powerUsage = powerUsage;
        this.diskUsage = diskUsage;
        this.ramUsage = ramUsage;
        this.uptime = uptime;
        this.softwareUsage = softwareUsage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMachineId() {
        return machineId;
    }

    public void setMachineId(long machineId) {
        this.machineId = machineId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getTimestampStart() {
        return timestampStart;
    }

    public void setTimestampStart(long timestampStart) {
        this.timestampStart = timestampStart;
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

    public double getNetworkUpUsage() {
        return networkUpUsage;
    }

    public void setNetworkUpUsage(double networkUpUsage) {
        this.networkUpUsage = networkUpUsage;
    }

    public double getNetworkDownUsage() {
        return networkDownUsage;
    }

    public void setNetworkDownUsage(double networkDownUsage) {
        this.networkDownUsage = networkDownUsage;
    }

    public double getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(double powerUsage) {
        this.powerUsage = powerUsage;
    }

    public double getDiskUsage() {
        return diskUsage;
    }

    public void setDiskUsage(double diskUsage) {
        this.diskUsage = diskUsage;
    }

    public double getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(double ramUsage) {
        this.ramUsage = ramUsage;
    }

    public int getUptime() {
        return uptime;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }

    public List<Integer> getSoftwareUsage() {
        return softwareUsage;
    }

    public void setSoftwareUsage(List<Integer> softwareUsage) {
        this.softwareUsage = softwareUsage;
    }

    @Override
    public String toString() {
        return "MachineUsage{" +
                "id='" + id + '\'' +
                ", machineId=" + machineId +
                ", userId=" + userId +
                ", timestampStart=" + timestampStart +
                ", cpuUsage=" + cpuUsage +
                ", gpuUsage=" + gpuUsage +
                ", networkUpUsage=" + networkUpUsage +
                ", networkDownUsage=" + networkDownUsage +
                ", powerUsage=" + powerUsage +
                ", uptime=" + uptime +
                ", softwareUsage=" + softwareUsage +
                '}';
    }
}

package ua.ies.group3.netcafe.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document("machineUsages")
public class MachineUsage {
    @Schema(description = "Identifier")
    @Id
    private String id;

    @Schema(description = "Machine ID")
    private long machineId;

    @Schema(description = "User ID")
    private long userId;

    @Schema(description = "Timestamp")
    private long timestampStart;

    @Schema(description = "CPU usage")
    private double cpuUsage;

    @Schema(description = "GPU usage")
    private double gpuUsage;

    @Schema(description = "Network upload usage")
    private double networkUpUsage;

    @Schema(description = "Network download usage")
    private double networkDownUsage;

    @Schema(description = "Power usage")
    private double powerUsage;

    @Schema(description = "Disk usage")
    private double diskUsage;

    @Schema(description = "RAM usage")
    private double ramUsage;

    @Schema(description = "Uptime")
    private int uptime;

    @Schema(description = "List of the IDs of software in use")
    private List<Integer> softwareUsage;

    @Schema(description = "CPU temperature")
    private double cpuTemp;

    @Schema(description = "GPU temperature")
    private double gpuTemp;

    public MachineUsage(long machineId, long userId, long timestampStart, double cpuUsage,
                        double gpuUsage, double networkUpUsage, double networkDownUsage, double powerUsage,
                        double diskUsage, double ramUsage, int uptime, List<Integer> softwareUsage,
                        double cpuTemp, double gpuTemp) {
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
        this.cpuTemp = cpuTemp;
        this.gpuTemp = gpuTemp;
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

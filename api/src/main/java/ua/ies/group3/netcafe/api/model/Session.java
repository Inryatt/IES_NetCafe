package ua.ies.group3.netcafe.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document("sessions")
public class Session {
    @Id
    private String id;

    private long machineId;
    private long userId;
    private long timestampStart;
    private Long timestampEnd;
    private int updateCount;
    private double avgCpuUsage;
    private double avgGpuUsage;
    private double avgNetDownUsage;
    private double avgNetUpUsage;
    private double avgPowerUsage;
    private double avgDiskUsage;
    private double avgRamUsage;
    private List<Integer> softwareUsed;

    public Session(long machineId, long userId, long timestampStart, Long timestampEnd, int updateCount,
                   double avgCpuUsage, double avgGpuUsage, double avgNetDownUsage, double avgNetUpUsage,
                   double avgPowerUsage, double avgDiskUsage, double avgRamUsage, List<Integer> softwareUsed) {
        super();
        // this.id = id;
        this.machineId = machineId;
        this.userId = userId;
        this.timestampStart = timestampStart;
        this.timestampEnd = timestampEnd;
        this.updateCount = updateCount;
        this.avgCpuUsage = avgCpuUsage;
        this.avgGpuUsage = avgGpuUsage;
        this.avgNetDownUsage = avgNetDownUsage;
        this.avgNetUpUsage = avgNetUpUsage;
        this.avgPowerUsage = avgPowerUsage;
        this.avgDiskUsage = avgDiskUsage;
        this.avgRamUsage = avgRamUsage;
        this.softwareUsed = softwareUsed;
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

    public Long getTimestampEnd() {
        return timestampEnd;
    }

    public void setTimestampEnd(Long timestampEnd) {
        this.timestampEnd = timestampEnd;
    }

    public int getUpdateCount() {
        return updateCount;
    }

    public void setUpdateCount(int updateCount) {
        this.updateCount = updateCount;
    }

    public double getAvgCpuUsage() {
        return avgCpuUsage;
    }

    public void setAvgCpuUsage(double avgCpuUsage) {
        this.avgCpuUsage = avgCpuUsage;
    }

    public double getAvgGpuUsage() {
        return avgGpuUsage;
    }

    public void setAvgGpuUsage(double avgGpuUsage) {
        this.avgGpuUsage = avgGpuUsage;
    }

    public double getAvgNetDownUsage() {
        return avgNetDownUsage;
    }

    public void setAvgNetDownUsage(double avgNetDownUsage) {
        this.avgNetDownUsage = avgNetDownUsage;
    }

    public double getAvgNetUpUsage() {
        return avgNetUpUsage;
    }

    public void setAvgNetUpUsage(double avgNetUpUsage) {
        this.avgNetUpUsage = avgNetUpUsage;
    }

    public double getAvgPowerUsage() {
        return avgPowerUsage;
    }

    public void setAvgPowerUsage(double avgPowerUsage) {
        this.avgPowerUsage = avgPowerUsage;
    }

    public double getAvgDiskUsage() {
        return avgDiskUsage;
    }

    public void setAvgDiskUsage(double avgDiskUsage) {
        this.avgDiskUsage = avgDiskUsage;
    }

    public double getAvgRamUsage() {
        return avgRamUsage;
    }

    public void setAvgRamUsage(double avgRamUsage) {
        this.avgRamUsage = avgRamUsage;
    }

    public List<Integer> getSoftwareUsed() {
        return softwareUsed;
    }

    public void setSoftwareUsed(List<Integer> softwareUsed) {
        this.softwareUsed = softwareUsed;
    }
}

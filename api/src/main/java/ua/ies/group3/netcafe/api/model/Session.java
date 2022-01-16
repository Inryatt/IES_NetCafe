package ua.ies.group3.netcafe.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Document("sessions")
public class Session {
    @Schema(description = "Identifier")
    @Id
    private String id;

    @Schema(description = "Machine ID")
    private long machineId;

    @Schema(description = "User ID")
    private long userId;

    @Schema(description = "Session beginning timestamp")
    private long timestampStart;

    @Schema(description = "Session end timestamp")
    private Long timestampEnd;

    @Schema(description = "Number of times updated with usages")
    private int updateCount;

    @Schema(description = "Average CPU usage")
    private double avgCpuUsage;

    @Schema(description = "Average GPU usage")
    private double avgGpuUsage;

    @Schema(description = "Average network download usage")
    private double avgNetDownUsage;

    @Schema(description = "Average network upload usage")
    private double avgNetUpUsage;

    @Schema(description = "Average power usage")
    private double avgPowerUsage;

    @Schema(description = "Average disk usage")
    private double avgDiskUsage;

    @Schema(description = "Average RAM usage")
    private double avgRamUsage;

    @Schema(description = "List of the IDs of the software used throughout the session")
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

package ua.ies.group3.netcafe.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "machines")
public class Machine {
    @Schema(description = "Identifier")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    // Specifications
    @Schema(description = "CPU name")
    @Column(name = "cpu", nullable = false)
    private String cpu;

    @Schema(description = "GPU name")
    @Column(name = "gpu", nullable = false)
    private String gpu;

    @Schema(description = "RAM name")
    @Column(name = "ram", nullable = false)
    private String ram;

    @Schema(description = "Disk name")
    @Column(name = "disk", nullable = false)
    private String disk;

    @Schema(description = "Operating system name")
    @Column(name = "os", nullable = false)
    private String os;

    @Schema(description = "Machine name")
    @Column(name = "name", nullable = false)
    private String name;

    // Location and position

    @Schema(description = "Location (cafe)")
    @ManyToOne(optional = false)
    @JoinColumn(name = "locationId", nullable = false)
    @JsonIgnoreProperties({"name", "map"})
    private Location location;

    @Schema(description = "Horizontal map coordinate")
    @Column(name = "xCoord", nullable = false)
    private double xCoord;

    @Schema(description = "Vertical map coordinate")
    @Column(name = "yCoord", nullable = false)
    private double yCoord;

    // Current Usage Information
    @Schema(description = "Current user")
    @OneToOne
    @JsonIgnoreProperties({"name", "email", "birthdate", "registerDate"})
    // @JoinColumn(name = "currentUserId")
    private User currentUser;

    @Schema(description = "Usage: software currently in use")
    @OneToMany
    @JsonIgnoreProperties({"name", "type"})
    // @JoinColumn(name = "softwareId")
    private List<Software> softwares;

    @Schema(description = "Usage: timestamp")
    @Column(name = "timestamp")
    private long timestamp;

    @Schema(description = "Usage: CPU usage")
    @Column(name = "cpuUsage")
    private double cpuUsage;

    @Schema(description = "Usage: GPU usage")
    @Column(name = "gpuUsage")
    private double gpuUsage;

    @Schema(description = "Usage: Disk usage")
    @Column(name = "diskUsage")
    private double diskUsage;

    @Schema(description = "Usage: RAM usage")
    @Column(name = "ramUsage")
    private double ramUsage;

    @Schema(description = "Usage: network download usage")
    @Column(name = "networkDownUsage")
    private double networkDownUsage;

    @Schema(description = "Usage: network upload usage")
    @Column(name = "networkUpUsage")
    private double networkUpUsage;

    @Schema(description = "Usage: power usage")
    @Column(name = "powerUsage")
    private double powerUsage;

    @Schema(description = "Usage: CPU temperature")
    @Column(name = "cpuTemp")
    private double cpuTemp;

    @Schema(description = "Usage: GPU temperature")
    @Column(name = "gpuTemp")
    private double gpuTemp;

    @Schema(description = "Usage: uptime (seconds)")
    @Column(name = "uptime")
    private int uptime;

    @Schema(description = "Machine status (0 - off, 1 - on, 2 - unavailable)")
    @Column(name = "status")
    private int status;

    // Getters and Setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCpu() {
        return cpu;
    }

    public void setCpu(String cpu) {
        this.cpu = cpu;
    }

    public String getGpu() {
        return gpu;
    }

    public void setGpu(String gpu) {
        this.gpu = gpu;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getDisk() {
        return disk;
    }

    public void setDisk(String disk) {
        this.disk = disk;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public double getxCoord() {
        return xCoord;
    }

    public void setxCoord(double xCoord) {
        this.xCoord = xCoord;
    }

    public double getyCoord() {
        return yCoord;
    }

    public void setyCoord(double yCoord) {
        this.yCoord = yCoord;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public List<Software> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<Software> softwares) {
        this.softwares = softwares;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
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

    public double getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(double ramUsage) {
        this.ramUsage = ramUsage;
    }

    public double getNetworkDownUsage() {
        return networkDownUsage;
    }

    public void setNetworkDownUsage(double networkDownUsage) {
        this.networkDownUsage = networkDownUsage;
    }

    public double getNetworkUpUsage() {
        return networkUpUsage;
    }

    public void setNetworkUpUsage(double networkUpUsage) {
        this.networkUpUsage = networkUpUsage;
    }

    public double getPowerUsage() {
        return powerUsage;
    }

    public void setPowerUsage(double powerUsage) {
        this.powerUsage = powerUsage;
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

    public int getUptime() {
        return uptime;
    }

    public void setUptime(int uptime) {
        this.uptime = uptime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    //    public double getNetworkUsage() {
//        return networkUsage;
//    }
//
//    public void setNetworkUsage(double networkUsage) {
//        this.networkUsage = networkUsage;
//    }
}

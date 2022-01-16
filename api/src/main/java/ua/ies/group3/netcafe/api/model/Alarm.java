package ua.ies.group3.netcafe.api.model;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document("alarms")
public class Alarm {
    @Id
    private String id;

    private long machineId;
    private long userId;
    private String message;
    private boolean seen;
    private String type;
    private long timestamp;

    public Alarm(long machineId, long userId, String message, boolean seen, String type, long timestamp) {
        super();
        this.machineId = machineId;
        this.userId = userId;
        this.message = message;
        this.seen = seen;
        this.type = type;
        this.timestamp = timestamp;
    }

    public Alarm(long machineId, long userId, String message, String type, long timestamp) {
        super();
        this.machineId = machineId;
        this.userId = userId;
        this.message = message;
        this.seen = false;
        this.type = type;
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Alarm{" +
                "id='" + id + '\'' +
                ", machineId=" + machineId +
                ", userId=" + userId +
                ", message='" + message + '\'' +
                ", seen=" + seen +
                ", type='" + type + '\'' +
                ", timestamp=" + timestamp +
                '}';
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
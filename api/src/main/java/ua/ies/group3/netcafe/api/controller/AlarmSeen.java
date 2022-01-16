package ua.ies.group3.netcafe.api.controller;

public class AlarmSeen {
    private String id;
    private boolean seen;

    public AlarmSeen(String id, boolean seen) {
        this.id = id;
        this.seen = seen;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}

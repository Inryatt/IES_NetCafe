package ua.ies.group3.netcafe.api.auxiliarymodel;

import io.swagger.v3.oas.annotations.media.Schema;

public class AlarmSeen {
    @Schema(description = "Alarm ID")
    private String id;

    @Schema(description = "Desired seen status")
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

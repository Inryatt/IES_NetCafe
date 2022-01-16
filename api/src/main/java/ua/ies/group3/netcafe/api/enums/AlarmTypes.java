package ua.ies.group3.netcafe.api.enums;

import ua.ies.group3.netcafe.api.service.AlarmService;

public enum AlarmTypes {
    INFO(0),
    WARNING(1),
    EMERGENCY(2);

    public final int severity;

    private AlarmTypes(int severity) {
        this.severity = severity;
    }

    public int getSeverity() {
        return severity;
    }
}
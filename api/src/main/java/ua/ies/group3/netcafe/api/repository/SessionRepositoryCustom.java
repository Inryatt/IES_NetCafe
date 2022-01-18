package ua.ies.group3.netcafe.api.repository;

import ua.ies.group3.netcafe.api.model.MachineUsage;
import ua.ies.group3.netcafe.api.model.Session;

public interface SessionRepositoryCustom {
    Session updateSession(MachineUsage machineUsage);
}

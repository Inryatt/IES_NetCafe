package ua.ies.group3.netcafe.api.repository;

import ua.ies.group3.netcafe.api.model.MachineUsage;

import java.util.List;

public interface MachineUsageRepositoryCustom {
    List<MachineUsage> findMachineUsagesAggregate(long machineId, long tsStart, long tsEnd, int round);
}

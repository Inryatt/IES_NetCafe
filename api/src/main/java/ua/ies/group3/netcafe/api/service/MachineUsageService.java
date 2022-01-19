package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.MachineUsage;
import ua.ies.group3.netcafe.api.repository.MachineUsageRepository;

import java.util.List;

@Service
@EnableMongoRepositories
public class MachineUsageService {
    @Autowired
    private MachineUsageRepository machineUsageRepository;

    public List<MachineUsage> findMachineUsageByTimestampStartBetween(long timestampStart, long timestampEnd) {
        return machineUsageRepository.findMachineUsageByTimestampStartBetweenOrderByTimestampStart(timestampStart, timestampEnd);
    }

    public List<MachineUsage> findMachineUsageByMachineIdAndTimestampStartBetween(long machineId, long timestampStart, long timestampEnd) {
        return machineUsageRepository.findMachineUsageByMachineIdAndTimestampStartBetweenOrderByTimestampStart(machineId, timestampStart, timestampEnd);
    }

    public List<MachineUsage> findAllMachineUsages() {
        return machineUsageRepository.findAll();
    }

    public MachineUsage saveMachineUsage(MachineUsage machineUsage) {
        return machineUsageRepository.save(machineUsage);
    }

    public List<MachineUsage> findMachineUsagesAggregate(long machineId, long tsStart, long tsEnd, int round) {
        return machineUsageRepository.findMachineUsagesAggregate(machineId, tsStart, tsEnd, round);
    }
}

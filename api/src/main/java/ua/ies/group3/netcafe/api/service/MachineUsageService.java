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

    public List<MachineUsage> findMachineUsageByTimestampStartIsAfterAndTimestampEndIsBefore(long timestampStart, long timestampEnd) {
        return machineUsageRepository.findMachineUsageByTimestampStartIsAfterAndTimestampEndIsBefore(timestampStart, timestampEnd);
    }

    public List<MachineUsage> findMachineUsageByMachineIdAndTimestampStartIsAfterAndTimestampEndIsBefore(long machineId, long timestampStart, long timestampEnd) {
        return machineUsageRepository.findMachineUsageByMachineIdAndTimestampStartIsAfterAndTimestampEndIsBefore(machineId, timestampStart, timestampEnd);
    }

    public List<MachineUsage> findAllMachineUsages() {
        return machineUsageRepository.findAll();
    }

    public MachineUsage saveMachineUsage(MachineUsage machineUsage) {
        return machineUsageRepository.save(machineUsage);
    }
}

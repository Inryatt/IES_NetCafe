package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.MachineUsage;
import ua.ies.group3.netcafe.api.repository.MachineUsageRepository;

import java.util.ArrayList;
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

    public List<MachineUsage> findMachineUsageWithRound(long machineId, long timestampStart, long timestampEnd, int round) {
        List<MachineUsage> usages;
        if (machineId != -1) {
            usages = findMachineUsageByMachineIdAndTimestampStartBetween(machineId, timestampStart, timestampEnd);
        }
        else {
            return findMachineUsageByTimestampStartBetween(timestampStart, timestampEnd);
        }

        // Default case, doesn't need to iterate everything
        if (round <= 1) { return usages; }

        List<MachineUsage> aggregation = new ArrayList<>();
        List<Integer> aggregation_counter = new ArrayList<>();
        // System.out.println("antes do for");
        for (MachineUsage usage : usages) {
            int lastIdx = aggregation.size() - 1;
            MachineUsage lastUsage = aggregation.isEmpty() ? null : aggregation.get(lastIdx);

            // Aggregate Machine
            if (lastUsage != null && roundTimestamp(usage, round) == roundTimestamp(lastUsage, round) && 
            usage.getUserId() == lastUsage.getUserId()) {
                // If they have different userId's, they shouldn't be aggregated
                Integer lastCounter = aggregation_counter.get(lastIdx);
                aggregation_counter.set(lastIdx, lastCounter + 1);
                updateAverage(lastUsage, usage, lastCounter);
            }

            else {
                usage.setTimestampStart(roundTimestamp(usage, round));
                aggregation.add(usage);
                aggregation_counter.add(1);
            }
        }

        // System.out.println("pos for");
        // System.out.println("AGGREGATION:");
        System.out.println("Round: " + round + ", Aggregation Size: " + aggregation.size());
        return aggregation;
    }

    public static long roundTimestamp(MachineUsage usage, int round) {
        return (usage.getTimestampStart()/round)*round;
    }

    public static void updateAverage(MachineUsage lastUsage, MachineUsage usage, Integer counter) {
        lastUsage.setCpuTemp( (lastUsage.getCpuTemp()*(counter - 1) + usage.getCpuTemp()) / counter );
        lastUsage.setCpuUsage( (lastUsage.getCpuUsage()*(counter - 1) + usage.getCpuUsage()) / counter );
        lastUsage.setDiskUsage( (lastUsage.getDiskUsage()*(counter - 1) + usage.getDiskUsage()) / counter );
        lastUsage.setGpuTemp( (lastUsage.getGpuTemp()*(counter - 1) + usage.getGpuTemp()) / counter );
        lastUsage.setGpuUsage( (lastUsage.getGpuUsage()*(counter - 1) + usage.getGpuUsage()) / counter );
        lastUsage.setNetworkDownUsage( (lastUsage.getNetworkDownUsage()*(counter - 1) + usage.getNetworkDownUsage()) / counter );
        lastUsage.setNetworkUpUsage( (lastUsage.getNetworkUpUsage()*(counter - 1) + usage.getNetworkUpUsage()) / counter );
        lastUsage.setPowerUsage( (lastUsage.getPowerUsage()*(counter - 1) + usage.getPowerUsage()) / counter );
        lastUsage.setRamUsage( (lastUsage.getRamUsage()*(counter - 1) + usage.getRamUsage()) / counter );
        lastUsage.setDiskUsage( (lastUsage.getDiskUsage()*(counter - 1) + usage.getDiskUsage()) / counter );
        lastUsage.setUptime( (lastUsage.getUptime()*(counter - 1) + usage.getUptime()) / counter );
    }

    public MachineUsage saveMachineUsage(MachineUsage machineUsage) {
        return machineUsageRepository.save(machineUsage);
    }

    public List<MachineUsage> findMachineUsagesAggregate(long machineId, long tsStart, long tsEnd, int round) {
        return machineUsageRepository.findMachineUsagesAggregate(machineId, tsStart, tsEnd, round);
    }
}

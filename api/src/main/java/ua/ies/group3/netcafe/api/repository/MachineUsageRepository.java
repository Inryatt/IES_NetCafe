package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.ies.group3.netcafe.api.model.MachineUsage;

import java.util.List;

public interface MachineUsageRepository extends MongoRepository<MachineUsage, String> {
    List<MachineUsage> findMachineUsageByTimestampBetweenOrderByTimestamp(long timestampStart, long timestampEnd);

    List<MachineUsage> findMachineUsageByMachineIdAndTimestampBetweenOrderByTimestamp(long machineId, long timestampStart, long timestampEnd);

    long count();
}

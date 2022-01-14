package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.ies.group3.netcafe.api.model.MachineUsage;

import java.util.List;

public interface MachineUsageRepository extends MongoRepository<MachineUsage, String> {
    List<MachineUsage> findMachineUsageByTimestampStartBetween(long timestampStart, long timestampEnd);

    List<MachineUsage> findMachineUsageByMachineIdAndTimestampStartBetween(long machineId, long timestampStart, long timestampEnd);

//    List<MachineUsage> findMachineUsageByTimestampStartIsAfterAndTimestampStartIsBefore(long timestampStart,
//                                                                                      long timestampEnd);
//
//    List<MachineUsage> findMachineUsageByMachineIdAndTimestampStartIsAfterAndTimestampStartIsBefore(long machineId,
//                                                                                                  long timestampStart,
//                                                                                                  long timestampEnd);

    long count();
}

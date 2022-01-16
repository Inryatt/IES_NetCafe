package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.ies.group3.netcafe.api.model.Alarm;
import ua.ies.group3.netcafe.api.model.Session;

import java.util.List;

public interface AlarmRepository extends MongoRepository<Alarm, String>  {
    List<Alarm> findAlarmsByTimestampBetween(long tsStart, long tsEnd);

    List<Alarm> findAlarmsByMachineId(long machineId);

    List<Alarm> findAlarmsByMachineIdAndTimestampBetween(long machineId, long tsStart, long tsEnd);
}

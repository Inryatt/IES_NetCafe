package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.Alarm;
import ua.ies.group3.netcafe.api.repository.AlarmRepository;

import java.util.List;
import java.util.Optional;

@Service
@EnableMongoRepositories
public class AlarmService {
    @Autowired
    private AlarmRepository alarmRepository;

    public List<Alarm> findAlarmsByTimestampBetween(long tsStart, long tsEnd) {
        return alarmRepository.findAlarmsByTimestampBetween(tsStart, tsEnd);
    }

    public List<Alarm> findAlarmsByMachineId(long machineId) {
        return alarmRepository.findAlarmsByMachineId(machineId);
    }

    public List<Alarm> findAlarmsByMachineIdAndTimestampBetween(long machineId, long tsStart, long tsEnd) {
        return alarmRepository.findAlarmsByMachineIdAndTimestampBetween(machineId, tsStart, tsEnd);
    }

    public Alarm setAlarmSeen(String alarmId, boolean bool) {
        Optional<Alarm> alarmOptional = alarmRepository.findById(alarmId);
        if (alarmOptional.isPresent()) {
            Alarm alarm = alarmOptional.get();
            alarm.setSeen(bool);
            saveAlarm(alarm);
            return alarm;
        }
        return null;
    }

    public Alarm saveAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);
    }
}

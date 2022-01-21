package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.exception.ResourceNotFoundException;
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

    public Optional<Alarm> findById(String id) {
        return alarmRepository.findById(id);
    }

    public Alarm setAlarmSeen(String alarmId, boolean bool) throws ResourceNotFoundException {
        Alarm alarm = findById(alarmId)
                .orElseThrow(() -> new ResourceNotFoundException("Alarm with ID " + alarmId + " not found."));
        alarm.setSeen(bool);
        saveAlarm(alarm);
        return alarm;
    }

    public Alarm saveAlarm(Alarm alarm) {
        return alarmRepository.save(alarm);
    }

    public Page<Alarm> findAlarmsByTimestampBetween(long tsStart, long tsEnd, Pageable pageable) {
        return alarmRepository.findAlarmsByTimestampBetween(tsStart, tsEnd, pageable);
    }

    public Page<Alarm> findAlarmsByMachineId(long machineId, Pageable pageable) {
        return alarmRepository.findAlarmsByMachineId(machineId, pageable);
    }

    public Page<Alarm> findAlarmsByMachineIdAndTimestampBetween(long machineId, long tsStart, long tsEnd, Pageable pageable) {
        return alarmRepository.findAlarmsByMachineIdAndTimestampBetween(machineId, tsStart, tsEnd, pageable);
    }
}

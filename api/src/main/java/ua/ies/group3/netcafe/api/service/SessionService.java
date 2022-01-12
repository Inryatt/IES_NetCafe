package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.Session;
import ua.ies.group3.netcafe.api.repository.SessionRepository;

import java.util.List;

@Service
@EnableMongoRepositories
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;

    public List<Session> findSessionsByMachineId(long machineId) {
        return sessionRepository.findSessionsByMachineId(machineId);
    }

    public List<Session> findSessionsByUserId(long userId) {
        return sessionRepository.findSessionsByUserId(userId);
    }

    public List<Session> findSessionsByMachineIdAndUserId(long machineId, long userId) {
        return sessionRepository.findSessionsByMachineIdAndUserId(machineId, userId);
    }

    public List<Session> findSessions() {
        return sessionRepository.findAll();
    }

    // test
    public Session saveSession(Session session) {
        return sessionRepository.save(session);
    }
}

package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import ua.ies.group3.netcafe.api.model.MachineUsage;
import ua.ies.group3.netcafe.api.model.Session;

import java.util.List;

public class SessionRepositoryImpl implements SessionRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public SessionRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public Session updateSession(MachineUsage machineUsage) {
        System.out.println("updateSession");
        List<Session> sessions = mongoTemplate.findAll(Session.class, "sessions");
        for (Session session : sessions)
            System.out.println("Session ID: " + session.getId());
        return null;
    }
}

package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.repository.SessionRepository;

@Service
@EnableMongoRepositories
public class SessionService {
    @Autowired
    private SessionRepository sessionRepository;
}

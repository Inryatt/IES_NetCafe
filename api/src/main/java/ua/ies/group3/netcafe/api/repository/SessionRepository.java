package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.ies.group3.netcafe.api.model.Session;

public interface SessionRepository extends MongoRepository<Session, String> {
    public long count();
}

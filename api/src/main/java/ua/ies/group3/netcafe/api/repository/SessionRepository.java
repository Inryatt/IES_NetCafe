package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import ua.ies.group3.netcafe.api.model.Session;

import java.util.List;

public interface SessionRepository extends MongoRepository<Session, String>, SessionRepositoryCustom {
    // @Query("{machineId: '?0'}")
    List<Session> findSessionsByMachineId(long machineId);

    List<Session> findSessionsByUserId(long userId);

    List<Session> findSessionsByMachineIdAndUserId(long machineId, long userId);

    long count();
}

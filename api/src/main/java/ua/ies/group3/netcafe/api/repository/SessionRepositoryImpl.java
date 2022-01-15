package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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

        Query query = new Query();
        query.addCriteria(Criteria.where("machineId").is(machineUsage.getMachineId()));
        query.addCriteria(Criteria.where("timestampEnd").is(null));
        List<Session> machineNullSessions = mongoTemplate.find(query, Session.class);

        // If there is no ongoing session, create a new one.
        if (machineNullSessions.size() == 0) {
            System.out.println("No ongoing machine session for machine with ID " + machineUsage.getId());
            if (machineUsage.getUserId() == 0) {
                System.out.println("Disregarding machine usage with user ID 0, because there are no ongoing sessions.");
                return null;
            }
            Session newSession = new Session(
                    machineUsage.getMachineId(),
                    machineUsage.getUserId(),
                    machineUsage.getTimestampStart(),
                    null,
                    1,
                    machineUsage.getCpuUsage(),
                    machineUsage.getGpuUsage(),
                    machineUsage.getNetworkDownUsage(),
                    machineUsage.getNetworkUpUsage(),
                    machineUsage.getPowerUsage(),
                    machineUsage.getDiskUsage(),
                    machineUsage.getRamUsage(),
                    machineUsage.getSoftwareUsage()
            );
            mongoTemplate.insert(newSession);
            return newSession;
        // If there is an ongoing session, update its values
        } else {
            System.out.println("Ongoing machine session for machine with ID " + machineUsage.getMachineId());
            Session cur = machineNullSessions.get(0);  // In theory, there should only be one.
            Update update = new Update();
            // Machine turned off, previous session over.
            if (machineUsage.getUserId() == 0) {
                System.out.println("Machine usage represents machine turned off (user ID: 0)");
                update.set("timestampEnd", machineUsage.getTimestampStart());
            // User still using it
            } else {
                System.out.println("Updating session with new machine usage information.");
                update.set("updateCount", cur.getUpdateCount() + 1);
                int count = cur.getUpdateCount() + 1;
                update.set("avgCpuUsage", (cur.getAvgCpuUsage() * (count - 1) + machineUsage.getCpuUsage()) / count);
                update.set("avgGpuUsage", (cur.getAvgGpuUsage() * (count - 1) + machineUsage.getGpuUsage()) / count);
                update.set("avgNetDownUsage", (cur.getAvgNetDownUsage() * (count - 1) + machineUsage.getNetworkDownUsage()) / count);
                update.set("avgNetUpUsage", (cur.getAvgNetUpUsage() * (count - 1) + machineUsage.getNetworkUpUsage()) / count);
                update.set("avgPowerUsage", (cur.getAvgPowerUsage() * (count - 1) + machineUsage.getPowerUsage()) / count);
                List<Integer> softwareIds = cur.getSoftwareUsed();
                for (int softwareId : machineUsage.getSoftwareUsage()) {
                    if (!softwareIds.contains(softwareId))
                        softwareIds.add(softwareId);
                update.set("softwareUsed", softwareIds);
                }
            }
            mongoTemplate.updateMulti(query, update, Session.class);
            return cur;
        }
    }
}

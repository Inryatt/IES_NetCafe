package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import ua.ies.group3.netcafe.api.model.MachineUsage;

import java.util.List;

public class MachineUsageRepositoryImpl implements MachineUsageRepositoryCustom {
    private final MongoTemplate mongoTemplate;

    public MachineUsageRepositoryImpl(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public List<MachineUsage> findMachineUsagesAggregate(long machineId, long tsStart, long tsEnd, int round) {
        MatchOperation matchOp = Aggregation.match(
                machineId == -1 ?
                        (new Criteria().orOperator(
                                new Criteria("timestampStart").gte(tsStart),
                                new Criteria("timestampStart").lte(tsEnd)
                        )) :
                        (new Criteria().orOperator(
                                new Criteria("machineId").is(machineId),
                                new Criteria("timestampStart").gte(tsStart),
                                new Criteria("timestampStart").lte(tsEnd)
                        ))
        );

        AddFieldsOperation addFieldsOp = Aggregation.addFields().addFieldWithValue(
                "timestampRounded",
                "$timestampStart"
        ).build();

        GroupOperation groupCpu = Aggregation.group("timestampRounded").avg("cpuUsage").as("cpuUsage");
        GroupOperation groupGpu = Aggregation.group("timestampRounded").avg("gpuUsage").as("gpuUsage");
        GroupOperation groupNetUp = Aggregation.group("timestampRounded").avg("networkUpUsage").as("networkUpUsage");
        GroupOperation groupNetDown = Aggregation.group("timestampRounded").avg("networkDownUsage").as("networkDownUsage");
        GroupOperation groupPower = Aggregation.group("timestampRounded").avg("powerUsage").as("powerUsage");
        GroupOperation groupDisk = Aggregation.group("timestampRounded").avg("diskUsage").as("diskUsage");
        GroupOperation groupRam = Aggregation.group("timestampRounded").avg("ramUsage").as("ramUsage");
        GroupOperation groupUptime = Aggregation.group("timestampRounded").avg("uptime").as("uptime");
        GroupOperation groupCpuTemp = Aggregation.group("timestampRounded").avg("cpuTemp").as("cpuTemp");
        GroupOperation groupGpuTemp = Aggregation.group("timestampRounded").avg("gpuTemp").as("gpuTemp");

        ProjectionOperation projectOp = Aggregation.project(
                "id", "machineId", "userId", "timestampStart", "cpuUsage", "gpuUsage", "networkUpUsage",
                "networkDownUsage", "powerUsage", "diskUsage", "ramUsage", "uptime", "softwareUsage", "cpuTemp",
                "gpuTemp"
        );

        Aggregation aggregation = Aggregation.newAggregation(matchOp, addFieldsOp, projectOp);
        AggregationResults<MachineUsage> output = mongoTemplate.aggregate(aggregation, "machineUsages", MachineUsage.class);
        return output.getMappedResults();
    }
}

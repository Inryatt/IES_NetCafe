package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.ies.group3.netcafe.api.model.Location;
import ua.ies.group3.netcafe.api.model.Machine;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> findMachinesByLocation(Location location);

    @Modifying
    @Query("update Machine m " +
            "set m.timestamp = :timestampStart," +
            "m.cpuUsage = :cpuUsage," +
            "m.gpuUsage = :gpuUsage," +
            "m.diskUsage = :diskUsage," +
            "m.ramUsage = :ramUsage," +
            "m.networkUpUsage = :networkUpUsage," +
            "m.networkDownUsage = :networkDownUsage," +
            "m.powerUsage = :powerUsage " +
            // "m.softwares = :softwares " +
            "where m.id = :machineId")
    void updateMachine(
            @Param("machineId") long machineId,
            @Param("timestampStart") long timestampStart,
            @Param("cpuUsage") double cpuUsage,
            @Param("gpuUsage") double gpuUsage,
            @Param("diskUsage") double diskUsage,
            @Param("ramUsage") double ramUsage,
            @Param("networkUpUsage") double networkUpUsage,
            @Param("networkDownUsage") double networkDownUsage,
            @Param("powerUsage") double powerUsage
            // TODO: How to update software usage (list :( )?
            // @Param("softwares") List<Integer> softwares
    );
}

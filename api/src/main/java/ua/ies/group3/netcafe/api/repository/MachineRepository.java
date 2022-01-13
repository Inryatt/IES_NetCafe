package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ies.group3.netcafe.api.model.Location;
import ua.ies.group3.netcafe.api.model.Machine;

import java.util.List;

public interface MachineRepository extends JpaRepository<Machine, Long> {
    List<Machine> findMachinesByLocation(Location location);
}

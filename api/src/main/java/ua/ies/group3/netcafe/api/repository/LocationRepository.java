package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ies.group3.netcafe.api.model.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}

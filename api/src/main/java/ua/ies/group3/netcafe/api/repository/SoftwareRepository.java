package ua.ies.group3.netcafe.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.ies.group3.netcafe.api.model.Software;

public interface SoftwareRepository extends JpaRepository<Software, Long> {

}

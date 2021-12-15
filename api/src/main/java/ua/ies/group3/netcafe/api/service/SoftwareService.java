package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.Software;
import ua.ies.group3.netcafe.api.repository.SoftwareRepository;

import java.util.List;
import java.util.Optional;

@Service
public class SoftwareService {
    @Autowired
    private SoftwareRepository softwareRepository;

    public Software saveSoftware(Software software) {
        return softwareRepository.save(software);
    }

    public List<Software> saveSoftwares(List<Software> softwares) {
        return softwareRepository.saveAll(softwares);
    }

    public Optional<Software> findSoftwareById(long id) {
        return softwareRepository.findById(id);
    }

    public List<Software> findAllSoftwares() {
        return softwareRepository.findAll();
    }
}

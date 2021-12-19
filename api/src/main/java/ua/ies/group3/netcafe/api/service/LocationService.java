package ua.ies.group3.netcafe.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.ies.group3.netcafe.api.model.Location;
import ua.ies.group3.netcafe.api.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<Location> saveLocations(List<Location> locations) {
        return locationRepository.saveAll(locations);
    }

    public Optional<Location> findLocationById(long id) {
        return locationRepository.findById(id);
    }

    public List<Location> findAllLocations() {
        return locationRepository.findAll();
    }
}

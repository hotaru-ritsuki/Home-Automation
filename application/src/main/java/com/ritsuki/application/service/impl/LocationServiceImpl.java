package com.ritsuki.application.service.impl;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.entity.Location;
import com.ritsuki.application.exception.exceptions.NotDeletedException;
import com.ritsuki.application.repository.HomeRepository;
import com.ritsuki.application.repository.LocationRepository;
import com.ritsuki.application.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;
    private final HomeRepository homeRepository;

    public Location create(Location request) {
        return locationRepository.save(request);
    }

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findOne(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(String.format(ErrorMessage.LOCATION_NOT_FOUND_BY_ID, id)));
    }

    public Location update(Location update) {
        Location location = findOne(update.getId());
        location.setName(update.getName());
        return locationRepository.save(location);
    }

    public void delete(Long id) {
        if (!locationRepository.findById(id).isPresent()) {
            throw new NotDeletedException(String.format(ErrorMessage.LOCATION_NOT_DELETED_BY_ID, id));
        }
        locationRepository.deleteById(id);
    }

    public List<Location> findByHome(Long id) {
        return locationRepository.findAllByHome(homeRepository.findById(id));
    }

}

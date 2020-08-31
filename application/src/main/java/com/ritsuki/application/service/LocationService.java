package com.ritsuki.application.service;

import com.ritsuki.application.entity.Location;

import java.util.List;

public interface LocationService {

    Location create(Location request);

    List<Location> findAll();

    Location findOne(Long id);

    Location update(Location update);

    void delete(Long id);

    List<Location> findByHome(Long id);

}

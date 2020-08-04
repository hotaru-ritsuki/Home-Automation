package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.exception.exceptions.NotDeletedException;

import java.util.List;

public interface LocationService {

    Location create(Location request);

    List<Location> findAll();

    Location findOne(Long id);

    Location update(Location update);

    void delete(Long id);

    List<Location> findByHome(Long id);

}

package com.ritsuki.application.service;

import com.ritsuki.application.entity.Home;
import com.ritsuki.application.entity.LocalDevice;
import com.ritsuki.application.entity.Location;

import java.util.List;

public interface LocalDeviceService {

    LocalDevice findByUuid(String uuid);

    List<LocalDevice> findAll();

    List<LocalDevice> findAllByLocation(Location location);

    List<LocalDevice> findAllByHome(Home home);

    LocalDevice update(LocalDevice localDevice);

    LocalDevice save(LocalDevice localDevice);

    String delete(String uuid);
}

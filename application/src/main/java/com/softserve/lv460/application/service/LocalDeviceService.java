package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;

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

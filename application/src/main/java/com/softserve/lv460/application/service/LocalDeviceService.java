package com.softserve.lv460.application.service;

import com.softserve.lv460.application.entity.LocalDevice;

import java.util.List;

public interface LocalDeviceService {
    LocalDevice findByUuid(String uuid) throws Exception;
    List<LocalDevice> findAll();
    LocalDevice update(LocalDevice localDevice);
    LocalDevice save(LocalDevice localDevice);
    void delete(String uuid);

}

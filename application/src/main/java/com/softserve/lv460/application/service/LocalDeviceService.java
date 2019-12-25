package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.LocalDevice;

import java.util.ArrayList;
import java.util.List;

public interface LocalDeviceService {
    LocalDevice findByUuid(String uuid) throws Exception;
    ArrayList<LocalDevice> findAll();
    LocalDevice update(LocalDevice localDevice);
    LocalDevice save(LocalDeviceRequest localDevice);
    void delete(String uuid);

}

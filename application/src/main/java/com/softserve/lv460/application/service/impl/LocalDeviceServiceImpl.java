package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.SupportedDevice;
import com.softserve.lv460.application.repository.HomeRepository;
import com.softserve.lv460.application.repository.LocalDeviceRepository;
import com.softserve.lv460.application.repository.LocationRepository;
import com.softserve.lv460.application.repository.SupportedDeviceRepository;
import com.softserve.lv460.application.service.LocalDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class LocalDeviceServiceImpl implements LocalDeviceService {
    @Autowired
    private LocalDeviceRepository localDeviceRepository;

    @Autowired
    private SupportedDeviceRepository supportedDeviceRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public LocalDevice findByUuid(String uuid) {
        return localDeviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Home with id " + uuid + " not exists"));
    }

    @Override
    public ArrayList<LocalDevice> findAll() {
        return (ArrayList<LocalDevice>) localDeviceRepository.findAll();
    }

    @Override
    public LocalDevice update(LocalDevice localDevice) {
        LocalDevice localDeviceByUuid = findByUuid(localDevice.getUuid());

        localDeviceByUuid.setLocations(localDevice.getLocations());

        return localDeviceRepository.save(localDeviceByUuid);
    }

    @Override
    public LocalDevice save(LocalDeviceRequest localDeviceRequest) {
        LocalDevice localDevice = new LocalDevice();

        localDevice.setLocations(locationRepository.findById(localDeviceRequest.getLocationId()).get());
        localDevice.setSupportedDevice(supportedDeviceRepository.findById(localDeviceRequest.getSupportedDeviceId()).get());
        localDevice.setUuid(UUID.randomUUID().toString().substring(0,32));

        return localDeviceRepository.save(localDevice);
    }

    @Override
    public void delete(String uuid) {
        localDeviceRepository.delete(findByUuid(uuid));
    }


}

package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.repository.LocalDeviceRepository;
import com.softserve.lv460.application.service.LocalDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class LocalDeviceServiceImpl implements LocalDeviceService {
    @Autowired
    private LocalDeviceRepository localDeviceRepository;

    @Override
    public LocalDevice findByUuid(String uuid) {
        return localDeviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Home with id " + uuid + " not exists"));
    }

    @Override
    public List<LocalDevice> findAll() {
        return localDeviceRepository.findAll();
    }

    @Override
    public LocalDevice update(LocalDevice localDevice) {
        LocalDevice localDeviceByUuid = findByUuid(localDevice.getUuid());

        localDeviceByUuid.setSupportedDevice(localDevice.getSupportedDevice());
        localDeviceByUuid.setHome(localDevice.getHome());

        return localDeviceRepository.save(localDeviceByUuid);
    }

    @Override
    public LocalDevice save(LocalDevice localDevice) {
        localDevice.setUuid(UUID.randomUUID().toString());

        return localDeviceRepository.save(localDevice);
    }

    @Override
    public void delete(String uuid) {
        localDeviceRepository.delete(findByUuid(uuid));
    }


}

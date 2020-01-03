package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.repository.LocalDeviceRepository;
import com.softserve.lv460.application.service.LocalDeviceService;
import com.softserve.lv460.application.service.LocationService;
import com.softserve.lv460.application.service.SupportedDeviceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocalDeviceServiceImpl implements LocalDeviceService {
    private LocalDeviceRepository localDeviceRepository;
    private SupportedDeviceService supportedDeviceService;
    private LocationService locationService;

    @Override
    public LocalDevice findByUuid(String uuid) {
        return localDeviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Device with uuid " + uuid + "does not exists"));
    }

    @Override
    public List<LocalDevice> findAll() {
        return localDeviceRepository.findAll();
    }

    @Override
    public List<LocalDevice> findAllByLocation(Location location) {
            return localDeviceRepository.findAllByLocations(location);
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

        localDevice.setLocations(locationService.findOne((localDeviceRequest.getLocationId())));
        localDevice.setSupportedDevice(supportedDeviceService.findById(localDeviceRequest.getSupportedDeviceId()));
        localDevice.setUuid(UUID.randomUUID().toString().substring(0,32));

        return localDeviceRepository.save(localDevice);
    }

    @Override
    public void delete(String uuid) {
        localDeviceRepository.delete(findByUuid(uuid));
    }


}

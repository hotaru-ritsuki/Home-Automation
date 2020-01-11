package com.softserve.lv460.application.service;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.repository.DeviceTemplateRepository;
import com.softserve.lv460.application.repository.LocalDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class LocalDeviceService {
    private LocalDeviceRepository localDeviceRepository;
    private DeviceTemplateRepository deviceTemplateRepository;
    private LocationService locationService;

    public LocalDevice findByUuid(String uuid) {
        return localDeviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new IllegalArgumentException("Device with uuid " + uuid + " does not exists"));
    }

    public List<LocalDevice> findAll() {
        return localDeviceRepository.findAll();
    }

    public List<LocalDevice> findAllByLocation(Location location) {
            return localDeviceRepository.findAllByLocation(location);
    }

    public LocalDevice update(LocalDevice localDevice) {
        LocalDevice localDeviceByUuid = findByUuid(localDevice.getUuid());

        localDeviceByUuid.setLocation(localDevice.getLocation());

        return localDeviceRepository.save(localDeviceByUuid);
    }

    public LocalDevice save(LocalDeviceRequest localDeviceRequest) {
        LocalDevice localDevice = new LocalDevice();

        localDevice.setLocation(locationService.findOne((localDeviceRequest.getLocationId())));
        DeviceTemplate deviceTemplate = deviceTemplateRepository.findById(localDeviceRequest.getSupportedDeviceId())
                .orElseThrow(() -> new IllegalArgumentException("Supported device does not exist by this id: "
                        + localDeviceRequest.getSupportedDeviceId()));
        localDevice.setSupportedDevice(deviceTemplate);
        localDevice.setUuid(UUID.randomUUID().toString().substring(0,32));

        return localDeviceRepository.save(localDevice);
    }

    public void delete(String uuid) {
        localDeviceRepository.delete(findByUuid(uuid));
    }


}

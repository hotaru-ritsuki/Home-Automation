package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.exception.exceptions.NotFoundIdException;
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
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.LOCAL_DEVICE_NOT_FOUND, uuid)));
    }

    public List<LocalDevice> findAll() {
        return localDeviceRepository.findAll();

    }

    public List<LocalDevice> findAllByLocation(Location location) {
<<<<<<< HEAD
            return localDeviceRepository.findAllByLocation(location);
=======
        return localDeviceRepository.findAllByLocation(location);
>>>>>>> e0d68ebb3be929cd23a3f56dd495e0a2c849d7d1
    }

    public LocalDevice update(LocalDevice localDevice) {
        LocalDevice localDeviceByUuid = findByUuid(localDevice.getUuid());

<<<<<<< HEAD
        localDeviceByUuid.setLocation(localDevice.getLocation());
=======
        localDeviceByUuid.setLocation(locationService.findOne(localDevice.getLocation().getId()));
>>>>>>> e0d68ebb3be929cd23a3f56dd495e0a2c849d7d1

        return localDeviceRepository.save(localDeviceByUuid);
    }

    public LocalDevice save(LocalDevice localDevice) {
        LocalDevice device = new LocalDevice();

<<<<<<< HEAD
        localDevice.setLocation(locationService.findOne((localDeviceRequest.getLocationId())));
        DeviceTemplate deviceTemplate = deviceTemplateRepository.findById(localDeviceRequest.getSupportedDeviceId())
                .orElseThrow(() -> new IllegalArgumentException("Supported device does not exist by this id: "
                        + localDeviceRequest.getSupportedDeviceId()));
        localDevice.setSupportedDevice(deviceTemplate);
        localDevice.setUuid(UUID.randomUUID().toString().substring(0,32));
=======
        device.setLocation(locationService.findOne((localDevice.getLocation().getId())));
        DeviceTemplate deviceTemplate = deviceTemplateRepository.findById(localDevice.getDeviceTemplate().getId())
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND,
                        localDevice.getDeviceTemplate().getId())));
        device.setDeviceTemplate(deviceTemplate);
        device.setUuid(UUID.randomUUID().toString().substring(0, 32));
>>>>>>> e0d68ebb3be929cd23a3f56dd495e0a2c849d7d1

        return localDeviceRepository.save(localDevice);
    }

    public String delete(String uuid) {
        localDeviceRepository.delete(findByUuid(uuid));

        return uuid;
    }
}

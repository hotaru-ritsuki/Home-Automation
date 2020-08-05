package com.softserve.lv460.application.service.impl;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.exception.exceptions.NotFoundIdException;
import com.softserve.lv460.application.repository.DeviceTemplateRepository;
import com.softserve.lv460.application.repository.LocalDeviceRepository;
import com.softserve.lv460.application.service.LocalDeviceService;
import com.softserve.lv460.application.service.LocationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Slf4j
@Service
public class LocalDeviceServiceImpl implements LocalDeviceService {

    private final LocalDeviceRepository localDeviceRepository;
    private final DeviceTemplateRepository deviceTemplateRepository;
    private final LocationService locationService;

    public LocalDevice findByUuid(String uuid) {
        return localDeviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.LOCAL_DEVICE_NOT_FOUND, uuid)));
    }

    public List<LocalDevice> findAll() {
        return localDeviceRepository.findAll();

    }

    public List<LocalDevice> findAllByLocation(Location location) {
        return localDeviceRepository.findAllByLocation(location);
    }

    public List<LocalDevice> findAllByHome(Home home) {
        return localDeviceRepository.findAllByLocationIn(locationService.findByHome(home.getId()));
    }

    public LocalDevice update(LocalDevice localDevice) {
        LocalDevice localDeviceByUuid = findByUuid(localDevice.getUuid());

        localDeviceByUuid.setLocation(locationService.findOne(localDevice.getLocation().getId()));
        localDeviceByUuid.setDescription(localDevice.getDescription());

        return localDeviceRepository.save(localDeviceByUuid);
    }

    public LocalDevice save(LocalDevice localDevice) {
        LocalDevice device = new LocalDevice();

        device.setLocation(locationService.findOne((localDevice.getLocation().getId())));
        DeviceTemplate deviceTemplate = deviceTemplateRepository.findById(localDevice.getDeviceTemplate().getId())
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND,
                        localDevice.getDeviceTemplate().getId())));
        device.setDeviceTemplate(deviceTemplate);
        device.setUuid(UUID.randomUUID().toString().substring(0, 32));
        device.setDescription(localDevice.getDescription());

        return localDeviceRepository.save(device);
    }

    public String delete(String uuid) {
        localDeviceRepository.delete(findByUuid(uuid));

        return uuid;
    }
}

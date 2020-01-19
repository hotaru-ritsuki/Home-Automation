package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequestDTO;
import com.softserve.lv460.application.dto.localDevice.LocalDeviceResponseDTO;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.exception.exceptions.NotFoundIdException;
import com.softserve.lv460.application.mapper.localDevice.LocalDeviceResponseMapper;
import com.softserve.lv460.application.repository.DeviceTemplateRepository;
import com.softserve.lv460.application.repository.LocalDeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LocalDeviceService {
    private LocalDeviceRepository localDeviceRepository;
    private DeviceTemplateRepository deviceTemplateRepository;
    private LocationService locationService;
    private LocalDeviceResponseMapper responseMapper;

    public LocalDeviceResponseDTO findByUuid(String uuid) {
        return responseMapper.toDto(localDeviceRepository.findByUuid(uuid)
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.LOCAL_DEVICE_NOT_FOUND, uuid))));
    }

    public List<LocalDeviceResponseDTO> findAll() {
        return localDeviceRepository.findAll().stream().map(responseMapper::toDto).collect(Collectors.toList());

    }

    public List<LocalDeviceResponseDTO> findAllByLocation(Location location) {
        return localDeviceRepository.findAllByLocation(location).stream().map(responseMapper::toDto)
                .collect(Collectors.toList());
    }

    public LocalDeviceResponseDTO update(LocalDeviceRequestDTO localDevice) {
        LocalDevice localDeviceByUuid = responseMapper.toEntity(findByUuid(localDevice.getUuid()));

        localDeviceByUuid.setLocation(locationService.findOne(localDevice.getLocationId()));

        return responseMapper.toDto(localDeviceRepository.save(localDeviceByUuid));
    }

    public LocalDeviceResponseDTO save(LocalDeviceRequestDTO localDeviceRequestDTO) {
        LocalDevice localDevice = new LocalDevice();

        localDevice.setLocation(locationService.findOne((localDeviceRequestDTO.getLocationId())));
        DeviceTemplate deviceTemplate = deviceTemplateRepository.findById(localDeviceRequestDTO.getDeviceTemplateId())
                .orElseThrow(() -> new NotFoundIdException(String.format(ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND,
                        localDeviceRequestDTO.getDeviceTemplateId())));
        localDevice.setDeviceTemplate(deviceTemplate);
        localDevice.setUuid(UUID.randomUUID().toString().substring(0, 32));

        return responseMapper.toDto(localDeviceRepository.save(localDevice));
    }

    public String delete(String uuid) {
        localDeviceRepository.delete(responseMapper.toEntity(findByUuid(uuid)));

        return uuid;
    }


}

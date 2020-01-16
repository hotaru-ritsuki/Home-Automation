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
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalDeviceServiceTest {
    @Mock
    private LocalDeviceRepository localDeviceRepository;

    @Mock
    private LocalDeviceResponseMapper responseMapper;

    @Mock
    private LocationService locationService;

    @Mock
    private DeviceTemplateRepository deviceTemplateRepository;

    @InjectMocks
    private LocalDeviceService localDeviceService;

    private Location location = new Location();
    private DeviceTemplate deviceTemplate = new DeviceTemplate();
    private LocalDevice localDevice = new LocalDevice("1", location, deviceTemplate);
    private LocalDeviceRequestDTO localDeviceRequestDTO = new LocalDeviceRequestDTO("1", 1L, 1L);
    private LocalDeviceResponseDTO localDeviceResponseDTO = new LocalDeviceResponseDTO();

    private String badUuid = "1";


    @Test
    void findByUuid() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));

        assertEquals(responseMapper.toDto(localDevice), localDeviceService.findByUuid(anyString()));
    }

    @Test
    void findByUuidFailed() {
        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.findByUuid(badUuid));

        String expectedMessage = ErrorMessage.LOCAL_DEVICE_NOT_FOUND + badUuid;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findAll() {
        List<LocalDevice> expected = Arrays.asList(localDevice, localDevice);

        when(localDeviceRepository.findAll()).thenReturn(expected);

        List<LocalDeviceResponseDTO> expectedDTO = expected.stream().map(responseMapper::toDto).collect(Collectors.toList());

        assertEquals(expectedDTO, localDeviceService.findAll());
    }

    @Test
    public void findAllFailed() {
        List<LocalDevice> expected = Collections.emptyList();

        when(localDeviceRepository.findAll()).thenReturn(expected);

        assertEquals(expected, localDeviceService.findAll());
    }

    @Test
    void findAllByLocation() {
        List<LocalDevice> expected = Arrays.asList(localDevice, localDevice);

        when(localDeviceRepository.findAllByLocation(any(Location.class))).thenReturn(expected);
        when(responseMapper.toDto(any(LocalDevice.class))).thenReturn(localDeviceResponseDTO);

        List<LocalDeviceResponseDTO> expectedDTO = expected.stream().map(responseMapper::toDto).collect(Collectors.toList());

        assertEquals(expectedDTO, localDeviceService.findAllByLocation(localDevice.getLocation()));
    }

    @Test
    void findAllByLocationFailed() {
        List<LocalDevice> expected = Collections.emptyList();

        when(localDeviceRepository.findAllByLocation(localDevice.getLocation())).thenReturn(expected);

        assertEquals(expected, localDeviceService.findAllByLocation(localDevice.getLocation()));
    }

    @Test
    void update() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));
        when(localDeviceRepository.save(localDevice)).thenReturn(localDevice);
        when(locationService.findOne(anyLong())).thenReturn(location);
        when(responseMapper.toDto(any(LocalDevice.class))).thenReturn(localDeviceResponseDTO);
        when(responseMapper.toEntity(any(LocalDeviceResponseDTO.class))).thenReturn(localDevice);


        assertEquals(responseMapper.toDto(localDevice), localDeviceService.update(localDeviceRequestDTO));
    }

    @Test
    void updateFailed() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.update(localDeviceRequestDTO));

        String expectedMessage = ErrorMessage.LOCAL_DEVICE_NOT_FOUND + badUuid;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void save() {
        when(locationService.findOne(anyLong())).thenReturn(location);
        when(deviceTemplateRepository.findById(anyLong())).thenReturn(Optional.of(deviceTemplate));
        when(localDeviceRepository.save(any(LocalDevice.class))).thenReturn(localDevice);

        assertEquals(responseMapper.toDto(localDevice), localDeviceService.save(localDeviceRequestDTO));
    }

    @Test
    void saveFailedBad() {
        when(deviceTemplateRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.save(localDeviceRequestDTO));

        String expectedMessage = ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND + localDeviceRequestDTO.getDeviceTemplateId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));

        assertEquals(localDevice.getUuid(), localDeviceService.delete(localDevice.getUuid()));
    }

    @Test
    void deleteFailed() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.delete(badUuid));

        String expectedMessage = ErrorMessage.LOCAL_DEVICE_NOT_FOUND + badUuid;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
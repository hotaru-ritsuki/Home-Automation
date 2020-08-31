package com.ritsuki.application.service;

import com.ritsuki.application.constant.ErrorMessage;
import com.ritsuki.application.exception.exceptions.NotFoundIdException;
import com.ritsuki.application.repository.DeviceTemplateRepository;
import com.ritsuki.application.repository.LocalDeviceRepository;
import com.ritsuki.application.entity.DeviceTemplate;
import com.ritsuki.application.entity.LocalDevice;
import com.ritsuki.application.entity.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LocalDeviceServiceTest {
    @Mock
    private LocalDeviceRepository localDeviceRepository;

    @Mock
    private LocationService locationService;

    @Mock
    private DeviceTemplateRepository deviceTemplateRepository;

    @InjectMocks
    private LocalDeviceService localDeviceService;

    private Location location = new Location();
    private DeviceTemplate deviceTemplate = new DeviceTemplate();
    private LocalDevice localDevice = new LocalDevice("1", "device", location, deviceTemplate);
    private String badUuid = "1";

    @Test
    void findByUuid() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));

        assertEquals(localDevice, localDeviceService.findByUuid(anyString()));
    }

    @Test
    void findByUuidFailed() {
        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.findByUuid(badUuid));

        String expectedMessage = String.format(ErrorMessage.LOCAL_DEVICE_NOT_FOUND, badUuid);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void findAll() {
        List<LocalDevice> expected = Arrays.asList(localDevice, localDevice);

        when(localDeviceRepository.findAll()).thenReturn(expected);

        assertEquals(expected, localDeviceService.findAll());
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

        assertEquals(expected, localDeviceService.findAllByLocation(localDevice.getLocation()));
    }

    @Test
    void findAllByLocationFailed() {
        List<LocalDevice> expected = Collections.emptyList();

        when(localDeviceRepository.findAllByLocation(localDevice.getLocation())).thenReturn(expected);

        assertEquals(expected, localDeviceService.findAllByLocation(localDevice.getLocation()));
    }

    @Test
    void update() {
        location.setId(1L);

        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));
        when(locationService.findOne(anyLong())).thenReturn(location);
        when(localDeviceRepository.save(localDevice)).thenReturn(localDevice);

        assertEquals(localDevice, localDeviceService.update(localDevice));
    }

    @Test
    void updateFailed() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.update(localDevice));
        String expectedMessage = String.format(ErrorMessage.LOCAL_DEVICE_NOT_FOUND, badUuid);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void save() {
        location.setId(1L);
        deviceTemplate.setId(1L);

        when(locationService.findOne(anyLong())).thenReturn(location);
        when(deviceTemplateRepository.findById(anyLong())).thenReturn(Optional.of(deviceTemplate));
        when(localDeviceRepository.save(any(LocalDevice.class))).thenReturn(localDevice);

        assertEquals(localDevice, localDeviceService.save(localDevice));
    }

    @Test
    void saveFailedBad() {
        deviceTemplate.setId(2L);

        when(deviceTemplateRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.save(localDevice));
        String expectedMessage = String.format(ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND, localDevice.getDeviceTemplate().getId());
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
        String expectedMessage = String.format(ErrorMessage.LOCAL_DEVICE_NOT_FOUND, badUuid);
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
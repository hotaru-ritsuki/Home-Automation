package com.softserve.lv460.application.service;

import com.softserve.lv460.application.constant.ErrorMessage;
import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.DeviceTemplate;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.exception.exceptions.NotFoundIdException;
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
    private LocalDevice localDevice = new LocalDevice("", location, deviceTemplate);
    private LocalDeviceRequest localDeviceRequest = new LocalDeviceRequest("1", 1L, 1L);
    private String badUuid = "1";

    @Test
    void findByUuid() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));

        assertEquals(localDevice, localDeviceService.findByUuid(anyString()));
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

        when(localDeviceRepository.findAllByLocation(localDevice.getLocation())).thenReturn(expected);

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
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));
        when(localDeviceRepository.save(localDevice)).thenReturn(localDevice);

        assertEquals(localDevice, localDeviceService.update(localDeviceRequest));
    }

    @Test
    void updateFailed() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.update(localDeviceRequest));

        String expectedMessage = ErrorMessage.LOCAL_DEVICE_NOT_FOUND + badUuid;
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void save() {
        when(locationService.findOne(anyLong())).thenReturn(location);
        when(deviceTemplateRepository.findById(anyLong())).thenReturn(Optional.of(deviceTemplate));
        when(localDeviceRepository.save(any(LocalDevice.class))).thenReturn(localDevice);

        assertEquals(localDevice, localDeviceService.save(localDeviceRequest));
    }

    @Test
    void saveFailedBad() {
        when(deviceTemplateRepository.findById(anyLong())).thenReturn(Optional.empty());

        Exception exception = assertThrows(NotFoundIdException.class, () -> localDeviceService.save(localDeviceRequest));

        String expectedMessage = ErrorMessage.DEVICE_TEMPLATE_NOT_FOUND + localDeviceRequest.getDeviceTemplateId();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void delete() {
        when(localDeviceRepository.findByUuid(anyString())).thenReturn(Optional.of(localDevice));

        assertEquals(localDevice.getUuid(), localDeviceService.delete(localDevice.getUuid()));
        verify(localDeviceRepository, times(1)).delete(localDevice);
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
package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequestDTO;
import com.softserve.lv460.application.dto.localDevice.LocalDeviceResponseDTO;
import com.softserve.lv460.application.service.LocalDeviceService;
import com.softserve.lv460.application.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location-devices")
@AllArgsConstructor
@CrossOrigin
public class LocalDeviceController {
    private LocalDeviceService localDeviceService;
    private LocationService locationService;

    @PostMapping
    public LocalDeviceResponseDTO save(@RequestBody LocalDeviceRequestDTO localDeviceRequestDTO) {
        return localDeviceService.save(localDeviceRequestDTO);
    }

    @GetMapping
    public List<LocalDeviceResponseDTO> findAll() {
        return localDeviceService.findAll();
    }

    @GetMapping("/location/{location_id}")
    public List<LocalDeviceResponseDTO> findByLocation(@PathVariable("location_id") Long id) {
        return localDeviceService.findAllByLocation(locationService.findOne(id));
    }

    @GetMapping("/{uuid}")
    public LocalDeviceResponseDTO findOne(@PathVariable("uuid") String uuid) {
        return localDeviceService.findByUuid(uuid);
    }

    @PutMapping()
    public LocalDeviceResponseDTO update(@RequestBody LocalDeviceRequestDTO localDevice) {
        return localDeviceService.update(localDevice);
    }

    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") String uuid) {
        return localDeviceService.delete(uuid);
    }
}

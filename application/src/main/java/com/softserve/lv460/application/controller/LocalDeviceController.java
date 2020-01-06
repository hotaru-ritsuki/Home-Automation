package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.LocalDevice;
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

    @PostMapping()
    public void save(@RequestBody LocalDeviceRequest localDeviceRequest) {
        localDeviceService.save(localDeviceRequest);
    }

    @GetMapping()
    public List<LocalDevice> findAll() {
        return localDeviceService.findAll();
    }

    @GetMapping("/location/{location_id}")
    public List<LocalDevice> findByLocation(@PathVariable("location_id") Long id){
        return localDeviceService.findAllByLocation(locationService.findOne(id));
    }

    @GetMapping("/{uuid}")
    public LocalDevice findOne(@PathVariable("uuid") String uuid) {
        System.out.println(uuid);
        return localDeviceService.findByUuid(uuid);
    }

    @PutMapping()
    public void update(@RequestBody LocalDevice localDevice) {
        localDeviceService.update(localDevice);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable("uuid") String uuid) {
        localDeviceService.delete(uuid);
    }
}

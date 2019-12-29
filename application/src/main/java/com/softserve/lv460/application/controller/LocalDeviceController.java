package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.service.LocalDeviceService;
import com.softserve.lv460.application.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/location-device")
@AllArgsConstructor
public class LocalDeviceController {
    private LocalDeviceService localDeviceService;
    private LocationService locationService;

    @PostMapping("/save")
    public void save(@RequestBody LocalDeviceRequest localDeviceRequest) {
        localDeviceService.save(localDeviceRequest);
    }

    @GetMapping("/find-all")
    public List<LocalDevice> findAll() {
        return localDeviceService.findAll();
    }

    @GetMapping("/find-all-in-location/{location_id}")
    public ArrayList<LocalDevice> findByLocation(@PathVariable("location_id") Long id){
        return localDeviceService.findAllByLocation(locationService.findOne(id));
    }

    @PutMapping("/update")
    public void update(@RequestBody LocalDevice localDevice) {
        localDeviceService.update(localDevice);
    }

    @DeleteMapping("/{uuid}")
    public void delete(@PathVariable("uuid") String uuid) {
        localDeviceService.delete(uuid);
    }


}

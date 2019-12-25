package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.dto.localDevice.LocalDeviceRequest;
import com.softserve.lv460.application.entity.LocalDevice;
import com.softserve.lv460.application.entity.Location;
import com.softserve.lv460.application.entity.SupportedDevice;
import com.softserve.lv460.application.service.LocalDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location-device")
public class LocalDeviceController {
    @Autowired
    private LocalDeviceService localDeviceService;

    @PostMapping("/save")
    public void save(@RequestBody LocalDeviceRequest localDeviceRequest) {
        localDeviceService.save(localDeviceRequest);
    }

    @GetMapping("/find-all")
    public List<LocalDevice> findAll() {
        return localDeviceService.findAll();
    }

    @PutMapping("/update")
    public void update(@RequestBody LocalDevice localDevice) {
        localDeviceService.update(localDevice);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String uuid) {
        localDeviceService.delete(uuid);
    }
}

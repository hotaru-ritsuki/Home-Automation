package com.softserve.lv460.application.controller;

import com.softserve.lv460.application.entity.Home;
import com.softserve.lv460.application.entity.LocalDevice;
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
    public void save(@RequestParam Home home, @RequestParam SupportedDevice supportedDevice){
        LocalDevice localDevice = new LocalDevice();
        localDevice.setHome(home);
        localDevice.getSupportedDevice().add(supportedDevice);
        localDeviceService.save(localDevice);
    }

    @GetMapping("/find-all")
    public List<LocalDevice> findAll() {
        return localDeviceService.findAll();
    }

    @PutMapping("/update")
    public void update(@RequestParam LocalDevice localDevice) {
        localDeviceService.update(localDevice);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestParam String uuid) {
        localDeviceService.delete(uuid);
    }
}

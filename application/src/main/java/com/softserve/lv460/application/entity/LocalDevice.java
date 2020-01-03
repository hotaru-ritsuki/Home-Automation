package com.softserve.lv460.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDevice {
    @Id
    private String uuid;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "location_id")
    @NotNull
    private Location locations;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supported_device_id")
    @NotNull
    private DeviceTemplate supportedDevice;

}

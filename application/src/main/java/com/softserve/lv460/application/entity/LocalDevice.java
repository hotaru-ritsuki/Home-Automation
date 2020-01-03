package com.softserve.lv460.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import javax.persistence.*;

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
    @JoinColumn(name = "device_id")
    @NotNull
    private DeviceTemplate supportedDevice;
}

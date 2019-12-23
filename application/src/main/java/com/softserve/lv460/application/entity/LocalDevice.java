package com.softserve.lv460.application.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalDevice {
    @Id
    private String uuid;

    @ManyToOne
    private Home home;

    @OneToMany
    @JoinColumn(name = "device_id")
    private List<SupportedDevice> supportedDevice = new ArrayList<>();

}

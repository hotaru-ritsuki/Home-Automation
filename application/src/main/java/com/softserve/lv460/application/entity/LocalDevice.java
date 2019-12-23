package com.softserve.lv460.application.entity;

import lombok.Data;

import javax.persistence.ManyToOne;

@Data
public class LocalDevice {
    

    @ManyToOne
    private Location location;
}

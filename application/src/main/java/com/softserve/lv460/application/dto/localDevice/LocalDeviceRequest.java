package com.softserve.lv460.application.dto.localDevice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalDeviceRequest {
    private String uuid;
    private Long locationId;
    private Long deviceTemplateId;
}

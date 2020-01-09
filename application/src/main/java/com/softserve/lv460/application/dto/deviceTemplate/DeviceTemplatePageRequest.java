package com.softserve.lv460.application.dto.deviceTemplate;

import com.softserve.lv460.application.dto.page.PaginationRequest;
import lombok.Data;

@Data
public class DeviceTemplatePageRequest {
  DeviceTemplateFilterRequest supportedDeviceFilterRequest;
  PaginationRequest paginationRequest;
}

package com.softserve.lv460.application.dto.supportedDevice;

import com.softserve.lv460.application.dto.page.PaginationRequest;
import lombok.Data;

@Data
public class SupportedDeviceFilterAndPaginationRequest {
  SupportedDeviceFilterRequest supportedDeviceFilterRequest;
  PaginationRequest paginationRequest;
}

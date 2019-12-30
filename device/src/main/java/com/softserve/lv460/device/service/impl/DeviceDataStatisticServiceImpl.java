package com.softserve.lv460.device.service.impl;

import com.softserve.lv460.device.dto.DeviceDataDto;
import com.softserve.lv460.device.repositiry.DeviceDataStatisticRepository;
import com.softserve.lv460.device.service.DeviceDataStatisticService;
import lombok.AllArgsConstructor;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DeviceDataStatisticServiceImpl implements DeviceDataStatisticService {
  private DeviceDataStatisticRepository deviceDataStatisticRepository;

  public List<DeviceDataDto> getStatistic(String type, String from, String to) {
    DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();
    DateTime fromParsed = dateTimeFormatter.parseDateTime(from);
    DateTime toParsed = dateTimeFormatter.parseDateTime(to);
    return deviceDataStatisticRepository.getStatistic(type, fromParsed, toParsed).stream()
            .map(deviceData -> new DeviceDataDto(deviceData.getData(), deviceData.getTimestamp()))
            .collect(Collectors.toList());
  }


}

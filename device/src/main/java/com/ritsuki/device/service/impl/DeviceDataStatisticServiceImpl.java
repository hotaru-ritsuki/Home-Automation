package com.ritsuki.device.service.impl;

import com.ritsuki.device.dto.device.DeviceDataDto;
import com.ritsuki.device.dto.parameters.StatisticParameters;
import com.ritsuki.device.repository.DeviceDataRepository;
import com.ritsuki.device.service.DeviceDataStatisticService;
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
  private DeviceDataRepository deviceDataRepository;

  @Override
  public List<DeviceDataDto> getStatistic(StatisticParameters statisticParameters) {
    return deviceDataRepository.getStatistic(statisticParameters.getType()
            , parser(statisticParameters.getFrom()),
            parser(statisticParameters.getTo()), statisticParameters.getLocationId()).stream()
            .map(deviceData -> new DeviceDataDto(deviceData.getUuId(), deviceData.getData(), deviceData.getTimestamp()))
            .collect(Collectors.toList());
  }


  private DateTime parser(String dataToParse) {
    String replaced = dataToParse.replace(" ", "+");
    DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();
    return dateTimeFormatter.parseDateTime(replaced);
  }

}

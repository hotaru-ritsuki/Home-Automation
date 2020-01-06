package com.softserve.lv460.device.dto;

import lombok.Data;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;

@Data
public class StatisticParameters {
  private String type;
  private DateTime from;
  private DateTime to;


  public void setFrom(String from) {
    this.from = parser(from);
  }

  public void setTo(String to) {
    this.to = parser(to);
  }

  private DateTime parser(String dataToParse) {
    String replaced = dataToParse.replace(" ", "+");
    DateTimeFormatter dateTimeFormatter = ISODateTimeFormat.dateTimeNoMillis();
    return dateTimeFormatter.parseDateTime(replaced);
  }
}

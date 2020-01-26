package com.softserve.lv460.device.dto.parameters;

import com.softserve.lv460.device.constant.ValidationConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticParameters {
  @NotBlank(message = ValidationConstants.EMPTY_STATISTIC_TYPE)
  private String type;
  @Pattern(regexp = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]T[0-1][0-9]:[0-1][0-9]:[0-1][0-9]\\+01:00",
          message = ValidationConstants.INVALID_FORMAT_DATE_FORMAT)
  private String from;
  @Pattern(regexp = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]T[0-1][0-9]:[0-1][0-9]:[0-1][0-9]\\+01:00",
          message = ValidationConstants.INVALID_FORMAT_DATE_FORMAT)
  private String to;
  @NotNull(message = ValidationConstants.NULL_ID)
  @Positive(message = ValidationConstants.NOT_POSITIVE_iD)
  private Long locationId;
}

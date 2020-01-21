package com.softserve.lv460.device.document;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.softserve.lv460.device.constant.ValidationConstants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document
@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceData {
  @Id
  private String id;
  @Size(min = 32, max = 32,message = ValidationConstants.INVALID_UUID_LENGTH)
  private String uuId;
  private LocalDateTime timestamp;
  private Map<String, String> data;
}

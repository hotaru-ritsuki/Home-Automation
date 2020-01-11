package com.softserve.lv460.device.document;

import com.softserve.lv460.device.constant.ValidationConstant;
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
public class DeviceData {
  @Id
  private String id;
  @Size(min = 32, max = 32,message = ValidationConstant.INVALID_UUID_LENGTH)
  private String uuId;
  private LocalDateTime timestamp;
  private Map<String, String> data;
}

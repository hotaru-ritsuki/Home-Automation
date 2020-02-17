package com.softserve.lv460.device.document;

import com.softserve.lv460.device.constant.ValidationConstants;
import com.softserve.lv460.device.dto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlertsList {
  @Id
  private String id;
  @Size(min = 32, max = 32, message = ValidationConstants.INVALID_UUID_LENGTH)
  private String uuId;
  private String data;
  private LocalDateTime timestamp;
}

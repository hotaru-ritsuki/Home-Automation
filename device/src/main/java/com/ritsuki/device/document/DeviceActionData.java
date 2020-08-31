package com.ritsuki.device.document;

import com.ritsuki.device.dto.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeviceActionData {
  @Id
  private String id;
  private String uuId;
  private String data;
  private LocalDateTime timestamp;
  private Status status;
}

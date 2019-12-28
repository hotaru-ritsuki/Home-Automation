package com.softserve.lv460.device.document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "DevicesData")
public class DeviceData {
  @Id
  private String id;
  private String uuId;
  private LocalDateTime timestamp;
  private Map<String,String> data;
}

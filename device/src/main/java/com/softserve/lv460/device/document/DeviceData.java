package com.softserve.lv460.device.document;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "DevicesData")
public class DeviceData {
  @Id
  private String id;
  @NotNull
  @Size(min = 32,max = 32)
  private String uuId;
  @NotNull
  private LocalDateTime timestamp;
  @NotNull
  private Map<String,String> data;
}

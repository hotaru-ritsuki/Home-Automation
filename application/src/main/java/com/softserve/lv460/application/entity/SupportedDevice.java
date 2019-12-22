package com.softserve.lv460.application.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupportedDevice {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(nullable = false)
  private String brand;
  @Column(nullable = false)
  private String model;
  @Column(nullable = false)
  private String type;
  @Column(nullable = false)
  private LocalDate releaseDate;
}

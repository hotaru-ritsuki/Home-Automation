package com.ritsuki.application.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TelegramActivation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  private LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(10);

  @OneToOne(targetEntity = TelegramUser.class, fetch = FetchType.EAGER)
  @JoinColumn(referencedColumnName = "id",unique = true)
  private TelegramUser telegramUser;

}

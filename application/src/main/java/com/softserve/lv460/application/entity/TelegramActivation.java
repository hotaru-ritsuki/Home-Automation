package com.softserve.lv460.application.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class TelegramActivation {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;

  @NonNull
  @OneToOne(targetEntity = TelegramUser.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "username", unique = true)
  private TelegramUser telegramUser;

  private LocalDateTime expiryDate = LocalDateTime.now().plusMinutes(5);
}

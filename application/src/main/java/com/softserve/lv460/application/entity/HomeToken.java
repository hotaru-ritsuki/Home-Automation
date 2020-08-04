package com.softserve.lv460.application.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class HomeToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @NonNull
  private String token;

  @NonNull
  @OneToOne(targetEntity = Home.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "home_id")
  private Home home;

  @NonNull
  @OneToOne(targetEntity = ApplicationUser.class, fetch = FetchType.EAGER)
  @JoinColumn(name = "user_id")
  private ApplicationUser user;

  private LocalDateTime expiryDate = LocalDateTime.now().plusDays(1);
}

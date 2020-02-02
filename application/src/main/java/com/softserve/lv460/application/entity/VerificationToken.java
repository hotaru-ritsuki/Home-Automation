package com.softserve.lv460.application.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  private String token;

  @NonNull
  @OneToOne(targetEntity = ApplicationUser.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id", unique = true)
  private ApplicationUser user;

  private LocalDateTime expiryDate = LocalDateTime.now().plusDays(1);
}
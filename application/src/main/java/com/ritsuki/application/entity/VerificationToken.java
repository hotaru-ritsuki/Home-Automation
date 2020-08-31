package com.ritsuki.application.entity;

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
  @JoinColumn(name = "user_id")
  private ApplicationUser user;

  private LocalDateTime expiryDate = LocalDateTime.now().plusDays(1);
}
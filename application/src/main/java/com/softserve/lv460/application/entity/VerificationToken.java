package com.softserve.lv460.application.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class VerificationToken {
  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NonNull
  private String token;

  @NonNull
  @OneToOne(targetEntity = ApplicationUser.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private ApplicationUser user;

  private Date expiryDate = calculateExpiryDate(EXPIRATION);

  private Date calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar cal = Calendar.getInstance();
    cal.setTime(new Timestamp(cal.getTime().getTime()));
    cal.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(cal.getTime().getTime());
  }

}
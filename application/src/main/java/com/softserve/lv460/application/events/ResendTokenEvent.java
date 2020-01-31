package com.softserve.lv460.application.events;

import lombok.Getter;
import com.softserve.lv460.application.entity.ApplicationUser;
import org.springframework.context.ApplicationEvent;

@Getter
@SuppressWarnings("serial")
public class ResendTokenEvent extends ApplicationEvent {
  private ApplicationUser user;
  private String appUrl;
  private String token;

  public ResendTokenEvent(ApplicationUser user, String appUrl, String token) {
    super(user);
    this.user = user;
    this.appUrl = appUrl;
    this.token = token;
  }
}

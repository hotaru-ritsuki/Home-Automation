package com.softserve.lv460.application.events;

import lombok.Getter;
import com.softserve.lv460.application.entity.ApplicationUser;
import org.springframework.context.ApplicationEvent;

@Getter
@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {
  private String appUrl;
  private ApplicationUser user;

  public OnRegistrationCompleteEvent(ApplicationUser user, String appUrl) {
    super(user);
    this.user = user;
    this.appUrl = appUrl;
  }
}

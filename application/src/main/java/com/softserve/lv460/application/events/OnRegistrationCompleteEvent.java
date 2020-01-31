package com.softserve.lv460.application.events;

import com.softserve.lv460.application.entity.ApplicationUser;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
@SuppressWarnings("serial")
public class OnRegistrationCompleteEvent extends ApplicationEvent {
    private final String appUrl;
    private final ApplicationUser user;

    public OnRegistrationCompleteEvent(final ApplicationUser user, final String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }
}

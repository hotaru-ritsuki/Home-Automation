package com.softserve.lv460.application.events;

import com.softserve.lv460.application.entity.ApplicationUser;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class RestoreEvent extends ApplicationEvent {
    private String appUrl;
    private ApplicationUser user;

    public RestoreEvent(ApplicationUser user, String appUrl) {
        super(user);
        this.user = user;
        this.appUrl = appUrl;
    }
}

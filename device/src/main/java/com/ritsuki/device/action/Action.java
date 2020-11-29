package com.ritsuki.device.action;

import java.util.Map;

public interface Action {

    void execute(Map<String, String> actionData);

    String getType();
}

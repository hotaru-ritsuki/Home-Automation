package com.ritsuki.application.controller.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.ritsuki.application.util.EnumUtil;

public enum DBStatus {
    HEALTHY("healthy"),
    ERROR("error");

    final String value;

    DBStatus(String value) {
        this.value=value;
    }

    public String getValue() {
        return value;
    }

    @JsonCreator
    public static DBStatus fromString(final String string){
        return EnumUtil.enumFromString(DBStatus.values(), string, DBStatus.class);
    }
}

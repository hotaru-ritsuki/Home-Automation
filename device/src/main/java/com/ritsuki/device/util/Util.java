package com.ritsuki.device.util;

import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class Util {


    public static Map<String, String> parseToMap(String toParse) {
        try {
            return JsonMapper.builderWithJackson2Defaults().build().readValue(toParse, new TypeReference<>() {
            });
        } catch (JacksonException e) {
            log.error(e.getLocalizedMessage());
            return Collections.emptyMap();
        }
    }
}

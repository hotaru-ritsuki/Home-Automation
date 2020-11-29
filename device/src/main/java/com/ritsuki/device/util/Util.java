package com.ritsuki.device.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Map;

@Slf4j
public class Util {


    public static Map<String, String> parseToMap(String toParse) {
        try {
            return new ObjectMapper().readValue(toParse, new TypeReference<>() {
            });
        } catch (JsonProcessingException e) {
            log.error(e.getLocalizedMessage());
            return Collections.emptyMap();
        }
    }
}

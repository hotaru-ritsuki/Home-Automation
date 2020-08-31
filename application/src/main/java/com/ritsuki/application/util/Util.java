package com.ritsuki.application.util;

import java.util.Arrays;
import java.util.stream.Stream;

public class Util {

    public static <E extends Enum> E enumFromString(E[] enumValues, String string, Class clazz) {
        return Stream.of(enumValues)
                .filter((e) -> e.name().equalsIgnoreCase(string))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Invalid " + clazz.getSimpleName() + " - " + string + ". Available values - " + Arrays.toString(enumValues)));
    }
}

package com.ritsuki.application.util;

import com.ritsuki.application.exception.exceptions.SerializeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ReflectionUtils;
import tools.jackson.core.JacksonException;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.DeserializationFeature;
import tools.jackson.databind.SerializationFeature;
import tools.jackson.databind.cfg.DateTimeFeature;
import tools.jackson.databind.json.JsonMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class JacksonUtil {

    public static final JsonMapper objectMapperWithTimestampDateFormat = JsonMapper.builderWithJackson2Defaults()
            .disable(DateTimeFeature.WRITE_DATES_AS_TIMESTAMPS)
            .enable(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS)
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .build();

    public static <T> T deserialize(final String json, final Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapperWithTimestampDateFormat.readValue(json, clazz);
        } catch (final JacksonException exception) {
            log.error("Cannot desiarialize: ", exception);
            throw new SerializeException("Cannot deserialize");
        }
    }

    public static <T> T deserialize(final String json, final TypeReference<T> type) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapperWithTimestampDateFormat.readValue(json, type);
        } catch (final JacksonException exception) {
            log.error("Cannot desiarialize: ", exception);
            throw new SerializeException("Cannot deserialize");
        }
    }

    public static String serialize(final Object object) {
        if (object == null) {
            return null;
        }
        try {
            return objectMapperWithTimestampDateFormat.writeValueAsString(object);
        } catch (final JacksonException exception) {
            log.warn("Cannot serialize: ", exception);
            throw new SerializeException("Cannot serialize");
        }
    }

    public static void hasAllFields(Class<?> target, String[] fields) {
        if (fields == null || fields.length == 0) return;
        List<String> missingFields = new ArrayList<>();
        for (String field : fields) {
            if (ReflectionUtils.findField(target, field) == null) {
                missingFields.add(field);
            }
        }

        if (missingFields.size() != 0) {
            String errorFields = missingFields.stream().collect(Collectors.joining(",", "[", "]"));
            log.error("Following fields don't exist: {}", errorFields);
            throw new SerializeException("Following fields don't exist: " + errorFields);
        }
    }

    public String wrapper(final Object o) {
        return serialize(o);
    }

}

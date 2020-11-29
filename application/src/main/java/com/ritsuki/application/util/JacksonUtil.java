package com.ritsuki.application.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ritsuki.application.exception.exceptions.SerializeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.ReflectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class JacksonUtil {

    public static final String JSON_PROPERTIES_FILTER = "propertyFilter";

    public static final ObjectMapper objectMapperWithTimestampDateFormat;

    static {
        objectMapperWithTimestampDateFormat = new ObjectMapper();
        objectMapperWithTimestampDateFormat.registerModule(new JavaTimeModule());
        objectMapperWithTimestampDateFormat.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapperWithTimestampDateFormat.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true);
        objectMapperWithTimestampDateFormat.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        final SimpleModule simpleModule = new SimpleModule();
        objectMapperWithTimestampDateFormat.registerModule(simpleModule);

    }


    public static <T> T deserialize(final String json, final Class<T> clazz) {
        if (json == null) {
            return null;
        }
        try {
            return objectMapperWithTimestampDateFormat.readValue(json, clazz);
        } catch (final IOException exception) {
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
        } catch (final IOException exception) {
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
        } catch (final JsonProcessingException exception) {
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
            log.error("Following fields don't exist: ", errorFields);
            throw new SerializeException("Following fields don't exist: " + errorFields);
        }
    }

    public static MappingJacksonValue filterProperties(final Object toFilter, final String[] properties) {
        return filterProperties(toFilter, properties, toFilter.getClass());
    }

    public static MappingJacksonValue filterProperties(final Object toFilter, final String[] properties, Class<?> filterTarget) {
        hasAllFields(filterTarget, properties);
        final MappingJacksonValue jacksonValue = new MappingJacksonValue(toFilter);
        final SimpleBeanPropertyFilter filter = Objects.isNull(properties) ? SimpleBeanPropertyFilter.serializeAll()
                : SimpleBeanPropertyFilter.filterOutAllExcept(properties);

        final FilterProvider filters = new SimpleFilterProvider().addFilter(JSON_PROPERTIES_FILTER, filter);
        jacksonValue.setFilters(filters);
        return jacksonValue;
    }

    public String wrapper(final Object o) {
        return serialize(o);
    }

}
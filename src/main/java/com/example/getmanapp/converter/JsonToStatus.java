package com.example.getmanapp.converter;

import com.example.getmanapp.model.Status;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class JsonToStatus implements Converter<Json, Status> {

    private final ObjectMapper objectMapper;

    public JsonToStatus(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Status convert(Json source) {
        try {
            return objectMapper.readValue(source.asString(), Status.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Json to Status: " + e.getMessage(), e);
        }
    }
}

package com.example.getmanapp.converter;

import com.example.getmanapp.utils.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class JsonToPayload implements Converter<Json, Payload> {

    private final ObjectMapper objectMapper;

    public JsonToPayload(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Payload convert(Json source) {
        try {
            return objectMapper.readValue(source.asString(), Payload.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Json to Payload: " + e.getMessage(), e);
        }
    }
}

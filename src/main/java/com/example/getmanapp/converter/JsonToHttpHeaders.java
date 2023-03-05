package com.example.getmanapp.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.http.HttpHeaders;

@ReadingConverter
public class JsonToHttpHeaders implements Converter<Json, HttpHeaders> {
    private final ObjectMapper objectMapper;

    public JsonToHttpHeaders(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public HttpHeaders convert(Json source) {
        try {
            return objectMapper.readValue(source.asString(), HttpHeaders.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Json to HttpHeaders: " + e.getMessage(), e);
        }
    }
}

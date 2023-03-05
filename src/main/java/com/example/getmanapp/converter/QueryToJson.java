package com.example.getmanapp.converter;

import com.example.getmanapp.utils.Query;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class QueryToJson implements Converter<Query, Json> {

    private final ObjectMapper objectMapper;

    public QueryToJson(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Json convert(Query source) {
        try {
            return Json.of(objectMapper.writeValueAsString(source));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Json to Query: " + e.getMessage(), e);
        }
    }
}

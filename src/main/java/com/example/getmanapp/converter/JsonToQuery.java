package com.example.getmanapp.converter;

import com.example.getmanapp.utils.Query;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class JsonToQuery implements Converter<Json, Query> {

    private final ObjectMapper objectMapper;

    public JsonToQuery(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Query convert(Json source) {
        try {
            return objectMapper.readValue(source.asString(), Query.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Json to Query: " + e.getMessage(), e);
        }
    }
}

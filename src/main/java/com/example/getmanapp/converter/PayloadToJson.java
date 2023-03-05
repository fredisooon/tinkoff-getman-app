package com.example.getmanapp.converter;

import com.example.getmanapp.utils.Payload;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

@WritingConverter
public class PayloadToJson implements Converter<Payload, Json> {

    private final ObjectMapper objectMapper;

    public PayloadToJson(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Json convert(Payload source) {
        try {
            return Json.of(objectMapper.writeValueAsString(source));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting Payload to Json: " + e.getMessage(), e);
        }
    }
}

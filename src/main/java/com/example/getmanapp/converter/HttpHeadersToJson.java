package com.example.getmanapp.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.http.HttpHeaders;

@WritingConverter
public class HttpHeadersToJson implements Converter<HttpHeaders, Json> {

    private final ObjectMapper objectMapper;

    public HttpHeadersToJson(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public Json convert(HttpHeaders source) {
        try {
            return Json.of(objectMapper.writeValueAsString(source));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting HttpHeaders to Json: " + e.getMessage(), e);
        }
    }
}

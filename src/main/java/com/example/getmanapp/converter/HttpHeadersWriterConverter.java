package com.example.getmanapp.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.stereotype.Component;

import java.net.http.HttpHeaders;

@Component
@WritingConverter
public class HttpHeadersWriterConverter implements Converter<HttpHeaders, String> {

    private final ObjectMapper objectMapper;

    public HttpHeadersWriterConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
    @Override
    public String convert(@NonNull HttpHeaders source) {
        try {
            return objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing HttpHeaders", e);
        }
    }
}

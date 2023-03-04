package com.example.getmanapp.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.net.http.HttpHeaders;

@Component
@ReadingConverter
public class HttpHeadersReaderConverter implements Converter<String, HttpHeaders> {

    private final ObjectMapper objectMapper;

    public HttpHeadersReaderConverter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public HttpHeaders convert(@NonNull String source) {
        if (StringUtils.hasText(source)) {
            try {
                return objectMapper.readValue(source, HttpHeaders.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Error deserializing HttpHeaders", e);
            }
        }
        return null;
    }
}
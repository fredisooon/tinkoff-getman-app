package com.example.getmanapp.model;

import com.example.getmanapp.utils.Payload;
import com.example.getmanapp.utils.Query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.lang.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.http.HttpHeaders;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class RequestSnapshot {

    @Id
    private Long id;

    private String http_version;

    private String method;

    private String scheme;

    private String host;

    private Integer port;

    private String path;

    @Nullable
    private HttpHeaders headers;

    @Nullable
    private Query query;

    @Nullable
    private Payload payload;
}

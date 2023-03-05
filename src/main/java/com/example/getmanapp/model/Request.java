package com.example.getmanapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Request {


    @Id
    private Long id;

    private String httpVersion;

    private String method;

    private String scheme;

    private String host;

    private Integer port;

    private String path;

    private String headers;

    private String query;

    private String payload;

    private Long workspace_id;
}

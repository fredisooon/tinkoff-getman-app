package com.example.getmanapp.utils.mix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestAdapter {

    private String httpVersion;

    private String method;

    private String scheme;

    private String host;

    private Integer port;

    private String path;

    private List<List<String>> headers;

    private List<List<String>> query;

    private List<List<String>> payload;

    private Long workspace_id;
}

package com.example.getmanapp.utils.mix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestAdapter {

    private String http_version;

    private String method;

    private String scheme;

    private String host;

    private Integer port;

    private String path;
    @Nullable
    private List<List<String>> headers;
    @Nullable
    private List<List<String>> query;
    @Nullable
    private List<List<String>> payload;

    private Long workspace_id;
}

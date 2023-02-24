package com.example.getmanapp.utils.mix;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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

    private List<List<String>> headers = new ArrayList<>();

    private List<List<String>> query= new ArrayList<>();

    private List<List<String>> payload= new ArrayList<>();

    private Long workspace_id;
}

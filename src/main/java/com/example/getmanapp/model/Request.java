package com.example.getmanapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table
public class Request {

    //@Id
    //private Long Id;

    private String httpVersion;

    private String method;

    private String scheme;

    private String host;

    private Integer port;

    private String path;

    private List<List<String>> headers;

    private List<List<String>> query;

    private List<List<String>> payload;

}

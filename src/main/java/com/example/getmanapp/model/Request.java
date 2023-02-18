package com.example.getmanapp.model;

import com.example.getmanapp.utils.Headers;
import com.example.getmanapp.utils.Payload;
import com.example.getmanapp.utils.Query;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table
public class Request {

    //@Id
    private Long Id;

    private String httpVersion;

    private String method;

    private String scheme;

    private String host;

    private Integer port;

    private String path;

    private Headers headers;

    private Query query;

    private Payload payload;
}

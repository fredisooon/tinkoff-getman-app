package com.example.getmanapp.controller.requestsClasses;

import java.util.List;

public class RequestAPI {
    private String http_version;
    private String method;
    private String scheme;
    private String host;
    private Integer port;
    private String path;
    private List<List<String>> headers;
    private List<List<String>> query;
    private List<List<String>> payload;

    @Override
    public String toString() {
        return "RequestAPI{" +
                "http_version='" + http_version + '\'' +
                ", method='" + method + '\'' +
                ", scheme='" + scheme + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                ", path='" + path + '\'' +
                ", headers=" + headers +
                ", query=" + query +
                ", payload=" + payload +
                '}';
    }

    public RequestAPI() {
    }

    public RequestAPI(String http_version, String method, String scheme, String host, Integer port, String path, List<List<String>> headers, List<List<String>> query, List<List<String>> payload) {
        this.http_version = http_version;
        this.method = method;
        this.scheme = scheme;
        this.host = host;
        this.port = port;
        this.path = path;

        this.headers = headers;
        this.query = query;
        this.payload = payload;
    }

    public String getHttp_version() {
        return http_version;
    }

    public void setHttp_version(String http_version) {
        this.http_version = http_version;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<List<String>> getHeaders() {
        return headers;
    }

    public void setHeaders(List<List<String>> headers) {
        this.headers = headers;
    }

    public List<List<String>> getQuery() {
        return query;
    }

    public void setQuery(List<List<String>> query) {
        this.query = query;
    }

    public List<List<String>> getPayload() {
        return payload;
    }

    public void setPayload(List<List<String>> payload) {
        this.payload = payload;
    }
}
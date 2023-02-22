package com.example.getmanapp.utils.mix;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.utils.Headers;
import com.example.getmanapp.utils.Payload;
import com.example.getmanapp.utils.Query;

public class AdapterLayer {

    public static Request transferRequestModel(RequestAdapter requestAdapter) {
        Request request = new Request();
        request.setHttpVersion(requestAdapter.getHttpVersion());
        request.setMethod(requestAdapter.getMethod());
        request.setScheme(requestAdapter.getScheme());
        request.setHost(requestAdapter.getHost());
        request.setPort(requestAdapter.getPort());
        request.setPath(requestAdapter.getPath());
        request.setHeaders(new Headers(requestAdapter.getHeaders()));
        request.setQuery(new Query(requestAdapter.getQuery()));
        request.setPayload(new Payload(requestAdapter.getPayload().get(0).get(1),
                                       requestAdapter.getPayload().get(1).get(1)));
        return request;
    }
}

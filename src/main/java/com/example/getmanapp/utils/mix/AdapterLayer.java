package com.example.getmanapp.utils.mix;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.utils.Payload;
import com.example.getmanapp.utils.Query;
import org.springframework.http.HttpHeaders;

import java.util.List;


public class AdapterLayer {

    public static Request transferRequestModel(RequestAdapter requestAdapter) {
        Request request = new Request();
        request.setHttpVersion(requestAdapter.getHttpVersion());
        request.setMethod(requestAdapter.getMethod());
        request.setScheme(requestAdapter.getScheme());
        request.setHost(requestAdapter.getHost());
        request.setPort(requestAdapter.getPort());
        request.setPath(requestAdapter.getPath());
        request.setQuery(new Query(requestAdapter.getQuery()));
        request.setPayload(new Payload(requestAdapter.getPayload().get(0).get(1),
                                       requestAdapter.getPayload().get(1).get(1)));
        request.setHeaders(fromNestedListToMap(requestAdapter.getHeaders()));
        return request;
    }

    private static HttpHeaders fromNestedListToMap(List<List<String>> list) {
        HttpHeaders headers = new HttpHeaders();

        for (List<String> nestedList : list) {
            String temporaryKey = nestedList.get(0);
            List<String> valueList = nestedList.stream().skip(1).toList();
            if (headers.containsKey(temporaryKey)) {
                List<String> extendedValues = headers.getValuesAsList(temporaryKey);
                extendedValues.addAll(valueList);
                headers.put(temporaryKey, extendedValues);
            }
            else
                headers.put(temporaryKey, valueList);
        }
        return headers;
    }
}

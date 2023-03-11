package com.example.getmanapp.utils.mix;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.RequestSnapshot;
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
        request.setWorkspace_id(0L);
        if (requestAdapter.getQuery() == null)
            request.setQuery(new Query());
        else
            request.setQuery(new Query(requestAdapter.getQuery()));
        if (requestAdapter.getHeaders() == null)
            request.setHeaders(new HttpHeaders());
        else
            request.setHeaders(fromNestedListToMap(requestAdapter.getHeaders()));

        if (requestAdapter.getPayload() == null)
            request.setPayload(new Payload());
        else {
            if (!requestAdapter.getPayload().isEmpty())
                request.setPayload(new Payload(requestAdapter.getPayload().get(0).get(1),
                        requestAdapter.getPayload().get(1).get(1)));
        }

        return request;
    }

    public static RequestSnapshot transferRequestSnapshotModel(Request requestAdapter) {
        RequestSnapshot requestSnapshot = new RequestSnapshot();
        requestSnapshot.setHttpVersion(requestAdapter.getHttpVersion());
        requestSnapshot.setMethod(requestAdapter.getMethod());
        requestSnapshot.setScheme(requestAdapter.getScheme());
        requestSnapshot.setHost(requestAdapter.getHost());
        requestSnapshot.setPort(requestAdapter.getPort());
        requestSnapshot.setPath(requestAdapter.getPath());
        requestSnapshot.setWorkspace_id(null);
        if (requestAdapter.getQuery() == null)
            requestSnapshot.setQuery(new Query());
        else
            requestSnapshot.setQuery(requestAdapter.getQuery());
        if (requestAdapter.getHeaders() == null)
            requestSnapshot.setHeaders(new HttpHeaders());
        else
            requestSnapshot.setHeaders(requestAdapter.getHeaders());

        if (requestAdapter.getPayload() == null)
            requestSnapshot.setPayload(new Payload());
        else {
            requestSnapshot.setPayload(requestAdapter.getPayload());
        }

        return requestSnapshot;
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

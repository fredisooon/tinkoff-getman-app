package com.example.getmanapp.utils.mix;

import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.RequestSnapshot;
import com.example.getmanapp.model.Response;
import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.utils.ID;
import com.example.getmanapp.utils.Payload;
import com.example.getmanapp.utils.Query;
import org.springframework.http.HttpHeaders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;


public class AdapterLayer {

    public static Request transferRequestModel(RequestAdapter requestAdapter) {
        Request request = new Request();
        request.setHttp_version(requestAdapter.getHttp_version());
        request.setMethod(requestAdapter.getMethod());
        request.setScheme(requestAdapter.getScheme());
        request.setHost(requestAdapter.getHost());
        request.setPort(requestAdapter.getPort());
        request.setPath(requestAdapter.getPath());
        request.setWorkspace_id(0L);

        if(requestAdapter.getQuery() == null)
            request.setQuery(null);
        else
            request.setQuery(new Query(requestAdapter.getQuery()));

        if(requestAdapter.getHeaders() == null)
            request.setHeaders(null);
        else
            request.setHeaders(fromNestedListToMap(requestAdapter.getHeaders()));
        // Can't be bothered
        if (requestAdapter.getPayload() != null && requestAdapter.getPayload().getData().isEmpty())
            request.setPayload(null);
        else
            request.setPayload(requestAdapter.getPayload());

        return request;
    }

    public static RequestSnapshot transferRequestSnapshotModel(Request requestAdapter) {
        RequestSnapshot requestSnapshot = new RequestSnapshot();
        requestSnapshot.setHttp_version(requestAdapter.getHttp_version());
        requestSnapshot.setMethod(requestAdapter.getMethod());
        requestSnapshot.setScheme(requestAdapter.getScheme());
        requestSnapshot.setHost(requestAdapter.getHost());
        requestSnapshot.setPort(requestAdapter.getPort());
        requestSnapshot.setPath(requestAdapter.getPath());
        requestSnapshot.setQuery(requestAdapter.getQuery());
        requestSnapshot.setHeaders(requestAdapter.getHeaders());
        var payload = requestAdapter.getPayload();
        if(payload == null || payload.getData() == null || payload.getType() == null)
            requestSnapshot.setPayload(null);
        else
            requestSnapshot.setPayload(payload);

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

    public static ResponseWorkspace convertToResponseWorkspace(Workspace w) {
        return new ResponseWorkspace(new ID(w.getId(), w.getWorkspace_fk_id()),
                                        w.getName(),
                                        w.getDescription(),
                                        w.getRequests(),
                                        w.getWorkspaces());
    }

    public static ResponseRequest convertToResponseRequest(Request r) {
        return new ResponseRequest(new ID(r.getId(), r.getWorkspace_id()),
                r.getHttp_version(),
                r.getMethod(),
                r.getScheme(),
                r.getHost(),
                r.getPort(),
                r.getPath(),
                multiValueMapToList(r.getHeaders()),
                (r.getQuery() == null) ? List.of() : r.getQuery().getQuery(),
                r.getPayload(),
                r.getWorkspace_id());
    }

    public static ResponseResponse convertToResponseResponse(Response r) {
        return new ResponseResponse(new ID(r.getId(), null),
                                        r.getRequestSnapshot(),
                                        r.getExecuted_at(),
                                        r.getClosed_a(),
                                        r.getStatus(),
                                        multiValueMapToList(r.getHeaders()),
                                        r.getPayload());
    }

    private static List<List<String>> multiValueMapToList(HttpHeaders map) {
        List<List<String>> list = new ArrayList<>();

        if (map != null) {

            for (String key : map.keySet()) {
                if (Objects.requireNonNull(map.get(key)).size() > 1) {
                    for (String value : Objects.requireNonNull(map.get(key))) {
                        List<String> nestedList = new ArrayList<>();
                        nestedList.add(key);
                        nestedList.add(value);
                        list.add(nestedList);
                    }
                } else {
                    List<String> nestedList = new ArrayList<>();
                    nestedList.add(key);
                    nestedList.addAll(Objects.requireNonNull(map.get(key)));
                    list.add(nestedList);
                }
            }
        }
        return list;

    }

}

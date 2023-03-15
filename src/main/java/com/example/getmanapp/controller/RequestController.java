package com.example.getmanapp.controller;

import com.example.getmanapp.config.WebClientConfiguration;
import com.example.getmanapp.service.RequestService;
import com.example.getmanapp.utils.ID;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.MoveObject;
import com.example.getmanapp.utils.mix.ResponseRequest;
import com.example.getmanapp.webclient.ExternalRequester;
import com.example.getmanapp.utils.mix.RequestAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;


@Slf4j
@RestController
@RequestMapping(path = "${v1API}/request")
public class RequestController {
    @Autowired
    private final WebClient defaultWebClient = WebClientConfiguration.webClientFromScratch();

    private final ExternalRequester externalRequester;
    private final RequestService requestService;


    public RequestController(ExternalRequester externalRequester, RequestService requestService) {
        this.externalRequester = externalRequester;
        this.requestService = requestService;
    }

    /**
     * Sample request:
     *
     * for GET /request/1
     * @param requestId = 1
     * @return
     *      {
     *     "id": 1,
     *     "httpVersion": "1.1",
     *     "method": "GET",
     *     "scheme": "http",
     *     "host": "httpbin.org",
     *     "port": 80,
     *     "path": "/get",
     *     "headers": {},
     *     "query": {
     *         "queries": [
     *             [
     *                 "first",
     *                 "1"
     *             ],
     *             [
     *                 "second",
     *                 "2"
     *             ],
     *             [
     *                 "third",
     *                 "3"
     *             ]
     *         ]
     *     },
     *     "payload": {
     *         "type": "1",
     *         "data": "2"
     *     },
     *     "workspace_id": 2
     * }
     */
    @GetMapping("/{id}")
    public Mono<ResponseRequest> getRequestById(@PathVariable("id") Long requestId) {

        return requestService.getRequestById(requestId);
    }

    /**
     * Sample request:
     *
     * for PUT request/1
     * @param requestId
     * @param body
     * @return updated Request instance
     */
    @PutMapping("/{id}")
    public Mono<ResponseRequest> updateRequest(@PathVariable("id") Long requestId,
                                       @RequestBody RequestAdapter body) {
        return requestService.updateRequestById(requestId, body);
    }

    @PostMapping()
    public Mono<ID> createNewRequest(@RequestParam(value = "workspace") Long workspaceId,
                                     @RequestBody RequestAdapter requestAdapter) {

        return requestService.saveRequest(workspaceId, requestAdapter);
    }

    @DeleteMapping("/{id}")
    public Mono<BooleanObject> deleteRequestById(@PathVariable("id") Long requestId) {

        return requestService.deleteRequestById(requestId);
    }

    @PostMapping("/{id}/move")
    public Mono<BooleanObject> moveRequestToWorkspace(@PathVariable("id") Long requestId,
                                                      @RequestBody MoveObject workspaceId) {

        return requestService.moveRequestToWorkspaceByWorkspaceId(requestId, workspaceId);
    }

    @PostMapping("/{id}/execute")
    public Mono<ID> executeRequest(@PathVariable("id") Long requestId) {

        return requestService.executeRequest(requestId);
    }
}
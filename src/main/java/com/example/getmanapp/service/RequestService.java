package com.example.getmanapp.service;

import com.example.getmanapp.exceptions.exception.*;
import com.example.getmanapp.model.Request;
import com.example.getmanapp.model.RequestSnapshot;
import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.repository.RequestSnapshotRepository;
import com.example.getmanapp.repository.ResponseRepository;
import com.example.getmanapp.repository.WorkspaceRepository;
import com.example.getmanapp.utils.ID;
import com.example.getmanapp.utils.mix.*;
import com.example.getmanapp.webclient.ExternalRequester;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RequestService{
    private final RequestRepository requestRepository;
    private final RequestSnapshotRepository requestSnapshotRepository;
    private final WorkspaceRepository workspaceRepository;
    private final ResponseRepository responseRepository;
    private final ExternalRequester externalRequester;

    @Autowired
    public RequestService(RequestRepository requestRepository,
                          RequestSnapshotRepository requestSnapshotRepository, WorkspaceRepository workspaceRepository,
                          ResponseRepository responseRepository,
                          ExternalRequester externalRequester) {

        this.requestRepository = requestRepository;
        this.requestSnapshotRepository = requestSnapshotRepository;
        this.workspaceRepository = workspaceRepository;
        this.responseRepository = responseRepository;
        this.externalRequester = externalRequester;
    }



    public Flux<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    /**
     * OK
     * @param id
     * @return Request instance
     */
    public Mono<ResponseRequest> getRequestById(Long id) {
        return requestRepository.findById(id)
                .switchIfEmpty(Mono.error(new RequestNotFoundException(id)))
                .flatMap(request -> Mono.just(AdapterLayer.convertToResponseRequest(request)));
    }

    /**
     * OK
     * @param id - id of updated Request
     * @param body - object with some properties omitted(workspace_id, because the movement takes place /move endpoint)
     * @return updated Request instance
     */
    @Transactional
    public Mono<ResponseRequest> updateRequestById(Long id, RequestAdapter body) {
        if (body == null)
            return Mono.error(new IllegalArgumentException("Request must not be null"));

        Request request = AdapterLayer.transferRequestModel(body);
        return requestRepository.findById(id)
                .switchIfEmpty(Mono.error(new RequestNotFoundException(id)))
                .flatMap(updatedRequest -> requestRepository.save(updateRequest(updatedRequest, request))
                        .flatMap(savedRequest -> Mono.just(AdapterLayer.convertToResponseRequest(savedRequest)))
                        .log());
    }

    private Request updateRequest(Request updated, Request data) {
        if (!updated.getHttp_version().equals(data.getHttp_version()) && data.getHttp_version() != null)
            updated.setHttp_version(data.getHttp_version());

        if (!updated.getMethod().equals(data.getMethod()) && data.getMethod() != null)
            updated.setMethod(data.getMethod());

        if (!updated.getScheme().equals(data.getScheme()) && data.getScheme() != null)
            updated.setScheme(data.getScheme());

        if (!updated.getHost().equals(data.getHost()) && data.getHost() != null)
            updated.setHost(data.getHost());

        if (!updated.getPort().equals(data.getPort()) && data.getPort() != null)
            updated.setPort(data.getPort());

        if (!updated.getPath().equals(data.getPath()) && data.getPath() != null)
            updated.setPath(data.getPath());

        // if(updated.getHeaders() == null)
        // assert updated.getHeaders() != null;
        // if (!updated.getHeaders().equals(data.getHeaders()) && data.getHeaders() != null)
        updated.setHeaders(data.getHeaders());
        updated.setQuery(data.getQuery());
        updated.setPayload(data.getPayload());

        return updated;
    }

    /**
     * OK
     * @param workspaceId
     * @param requestAdapter
     * @return
     */
    @Transactional
    public Mono<ID> saveRequest(Long workspaceId, RequestAdapter requestAdapter) {
        if (workspaceId == 0 || workspaceId == -100)
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request"));

        if (requestAdapter == null)
            return Mono.error(new IllegalArgumentException("Request must not be null"));

        Request request = AdapterLayer.transferRequestModel(requestAdapter);
        request.setWorkspace_id(workspaceId);

        return requestRepository.save(request)
                .switchIfEmpty(Mono.error(new RequestSaveException(request)))
                .onErrorResume(e -> Mono.error(new WorkspaceNotFoundException(request.getWorkspace_id())))
                .flatMap(savedRequest -> Mono.just(new ID(savedRequest.getId(), savedRequest.getWorkspace_id())))
                .log();
    }

    /**
     * OK
     * @param id
     * @return
     */
    @Transactional
    public Mono<BooleanObject> deleteRequestById(Long id) {

        return requestRepository.findById(id)
                .flatMap(request -> requestRepository.deleteById(id)
                        .thenReturn((new BooleanObject(Boolean.TRUE))))
                .switchIfEmpty(Mono.just(new BooleanObject(Boolean.FALSE,
                         "Request Not Found For ID: "+ id)));
    }

    /**
     * OK
     * @param requestId
     * @param moveObject
     * @return
     */
    @Transactional
    public Mono<BooleanObject> moveRequestToWorkspaceByWorkspaceId(Long requestId, MoveObject moveObject) {
        if (moveObject == null)
            return Mono.error(new IllegalArgumentException("Body must not be null"));

        if (moveObject.getWorkspace() == 0)
            return Mono.error(new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request"));
        Long workspaceId = moveObject.getWorkspace();

        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new RequestNotFoundException(requestId)))
                .flatMap(request -> workspaceRepository.findById(workspaceId)
                        .switchIfEmpty(Mono.error(new WorkspaceNotFoundException(workspaceId)))
                        .flatMap(storeRequest -> {
                            request.setWorkspace_id(workspaceId);
                            return requestRepository.save(request)
                                    .switchIfEmpty(Mono.error(new RequestSaveException(request)))
                                    .thenReturn(new BooleanObject(Boolean.TRUE));
                        }));
    }

    /**
     * OK
     * @param requestId
     * @return
     */
    @Transactional
    public Mono<ID> executeRequest(Long requestId) {

        return requestRepository.findById(requestId)
                .switchIfEmpty(Mono.error(new RequestNotFoundException(requestId)))
                .flatMap(request -> {
                    RequestSnapshot requestSnapshot = AdapterLayer.transferRequestSnapshotModel(request);

                    return requestSnapshotRepository.save(requestSnapshot)
                            .switchIfEmpty(Mono.error(new RequestSnapshotSaveException(requestSnapshot)))
                            .flatMap(result -> externalRequester.getExternalRequest(request)
                                    .flatMap(response -> {
                                        response.setRequestSnapshot(requestSnapshot.getId());
                                        return responseRepository.save(response)
                                                .switchIfEmpty(Mono.error(new ResponseSaveException(response)))
                                                        .flatMap(savedResponse -> Mono.just(new ID(savedResponse.getId(), null)));
                                    })
                            );
                });

    }
}

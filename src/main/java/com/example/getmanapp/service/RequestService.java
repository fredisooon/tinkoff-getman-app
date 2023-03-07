package com.example.getmanapp.service;

import com.example.getmanapp.exceptions.exception.RequestNotFoundException;
import com.example.getmanapp.exceptions.exception.RequestSaveException;
import com.example.getmanapp.exceptions.exception.ResponseSaveException;
import com.example.getmanapp.exceptions.exception.WorkspaceNotFoundException;
import com.example.getmanapp.model.Request;
import com.example.getmanapp.repository.RequestRepository;
import com.example.getmanapp.repository.ResponseRepository;
import com.example.getmanapp.repository.WorkspaceRepository;
import com.example.getmanapp.utils.ID;
import com.example.getmanapp.utils.mix.AdapterLayer;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.MoveObject;
import com.example.getmanapp.utils.mix.RequestAdapter;
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
    private final WorkspaceRepository workspaceRepository;
    private final ResponseRepository responseRepository;
    private final ExternalRequester externalRequester;

    @Autowired
    public RequestService(RequestRepository requestRepository,
                          WorkspaceRepository workspaceRepository,
                          ResponseRepository responseRepository,
                          ExternalRequester externalRequester) {

        this.requestRepository = requestRepository;
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
    public Mono<Request> getRequestById(Long id) {
        return requestRepository.findById(id)
                .switchIfEmpty(Mono.error(new RequestNotFoundException(id)));
    }

    /**
     * OK
     * @param id - id of updated Request
     * @param body - object with some properties omitted(workspace_id, because the movement takes place /move endpoint)
     * @return updated Request instance
     */
    @Transactional
    public Mono<Request> updateRequestById(Long id, RequestAdapter body) {
        if (body == null)
            return Mono.error(new IllegalArgumentException("Request must not be null"));

        Request request = AdapterLayer.transferRequestModel(body);
        return requestRepository.findById(id)
                .switchIfEmpty(Mono.error(new RequestNotFoundException(id)))
                .flatMap(updatedRequest -> {
                    updatedRequest.setHttpVersion(request.getHttpVersion());
                    updatedRequest.setMethod(request.getMethod());
                    updatedRequest.setScheme(request.getScheme());
                    updatedRequest.setHost(request.getHost());
                    updatedRequest.setPort(request.getPort());
                    updatedRequest.setPath(request.getPath());
                    updatedRequest.setHeaders(request.getHeaders());
                    updatedRequest.setQuery(request.getQuery());
                    updatedRequest.setPayload(request.getPayload());

                    return requestRepository.save(updatedRequest)
                            .log();
                });
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
                .flatMap(request -> externalRequester.getExternalRequest(request)
                        .flatMap(response -> responseRepository.save(response)
                                .switchIfEmpty(Mono.error(new ResponseSaveException(response)))
                                .flatMap(savedResponse -> Mono.just(new ID(savedResponse.getId(), null)))
                        ));
    }
}

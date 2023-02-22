package com.example.getmanapp.model;

import com.example.getmanapp.utils.Headers;
import com.example.getmanapp.utils.Id;
import com.example.getmanapp.utils.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import reactor.core.publisher.Mono;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    private Id id;
    private Integer requestSnapshot;
    private Long executed_at;
    private Long closed_a;
    private Status status = new Status();
    private HttpHeaders headers = new HttpHeaders();
    private Payload payload = new Payload();

    public Response(Status status) {
        this.status = status;
    }
    public Response(ClientResponse response) {
//    public Response(ClientResponse response) {
//        this.status = response.statusCode();
//        this.body = response.body(t -> t);
    }

}

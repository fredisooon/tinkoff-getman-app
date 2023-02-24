package com.example.getmanapp.model;

import com.example.getmanapp.utils.Id;
import com.example.getmanapp.utils.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;


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

}

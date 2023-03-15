package com.example.getmanapp.utils.mix;

import com.example.getmanapp.model.Status;
import com.example.getmanapp.utils.ID;
import com.example.getmanapp.utils.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseResponse {

    private ID id;
    private Long requestSnapshot;
    private Long executed_at;
    private Long closed_at;
    private Status status = new Status();
    private List<List<String>> headers;
    private Payload payload = new Payload();
}

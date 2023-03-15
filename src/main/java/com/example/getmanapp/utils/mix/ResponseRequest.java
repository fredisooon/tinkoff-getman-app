package com.example.getmanapp.utils.mix;

import com.example.getmanapp.utils.ID;
import com.example.getmanapp.utils.Payload;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseRequest {
    private ID id;

    private String http_version;

    private String method;

    private String scheme;

    private String host;

    private Integer port;

    private String path;

    private List<List<String>> headers;

    private List<List<String>> query;

    private Payload payload;

    private Long workspace_id;
}

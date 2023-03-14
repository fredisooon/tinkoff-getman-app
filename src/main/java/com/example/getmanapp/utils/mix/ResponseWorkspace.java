package com.example.getmanapp.utils.mix;

import com.example.getmanapp.utils.ID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseWorkspace {
    private ID id;
    private String name;
    private String description;
    private Long workspace_fk_id;
    private List<Long> requests;
    private List<Long> workspaces;
}

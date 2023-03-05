package com.example.getmanapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
public class Workspace {

    @Id
    private Long id;
    private String name;
    private String description;
    private Long workspace_fk_id;

    @Transient
    private List<Long> requests;

    @Transient
    private List<Long> workspaces;
}

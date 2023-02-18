package com.example.getmanapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
//@Table
public class Workspace {

    //@Id
    private Long id;

    private String name;

    private String description;

    private List<Request> requests = new ArrayList<>();

    private List<Workspace> workspaces = new ArrayList<>();
}

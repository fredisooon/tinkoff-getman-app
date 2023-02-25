package com.example.getmanapp.controller;

import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.service.WorkspaceService;
import com.example.getmanapp.utils.Id;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
@RequestMapping("/api/version/1/workspace")
public class WorkspaceController {

    private WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @PostMapping
    public Mono<Id> createWorkspace(@RequestParam(value = "workspace", required = false,
            defaultValue = "0") String workspace_fk_id
            , @RequestBody Workspace workspace) {
        log.info("First step: " + workspace.toString());
        return workspaceService.saveWorkspace(workspace, workspace_fk_id);
    }

    @GetMapping("/{id}")
    public Mono<Workspace> getWorkspaceById(@PathVariable("id") Long id) {
        return workspaceService.getWorkspaceById(id);
    }

    @PutMapping("/{id}")
    public String updateWorkspace(@PathVariable("id") String id,
                                  @RequestBody Workspace workspace) {
        return null;
    }

    @DeleteMapping("/{id}")
    public String deleteWorkspaceById(@PathVariable("id") String id,
                                      @RequestParam(value = "cascade",
                                                    defaultValue = "false") Boolean isCascade){
        if (!isCascade) {
            if (Integer.parseInt(id) % 2 == 0)
                return "Workspace with EVEN id cannot be deleted!";
            else
                return "Workspace with " + id + " id, was successfully deleted!";
        }
        else
            return "Cascade is activated";
    }

    @PostMapping("/{id}/move")
    public Boolean moveWorkspaceToWorkspace(@PathVariable("id") String id,
                                            @RequestBody String body) {
        if ((Integer.parseInt(id) + Integer.parseInt(body)) % 3 == 0)
            return Boolean.TRUE;
        else
            return Boolean.FALSE;
    }

}

package com.example.getmanapp.controller;

import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.service.WorkspaceService;
import com.example.getmanapp.utils.Id;
import com.example.getmanapp.utils.mix.BooleanObject;
import com.example.getmanapp.utils.mix.MoveObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping(path = "${v1API}/workspace")
public class WorkspaceController {

    private WorkspaceService workspaceService;

    @Autowired
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping("/{id}")
    public Mono<Workspace> getWorkspaceById(@PathVariable("id") Long id) {
        return workspaceService.getWorkspaceById(id);
    }

    @PutMapping("/{id}")
    public Mono<Workspace> updateWorkspace(@PathVariable("id") String id,
                                           @RequestBody Workspace workspace) {
        return workspaceService.updateWorkspaceById(workspace, Long.parseLong(id));
    }

    @PostMapping()
    public Mono<Id> createWorkspace(@RequestParam(value = "workspace", required = false, defaultValue = "0")
                                        String workspace_fk_id,
                                    @RequestBody Workspace workspace) {

        workspace.setWorkspace_fk_id(Long.parseLong(workspace_fk_id));
        return workspaceService.saveWorkspace(workspace);
    }

    @DeleteMapping("/{id}")
    public Mono<BooleanObject> deleteWorkspaceById(@PathVariable("id") String id,
                                                   @RequestParam(value = "cascade",
                                                     defaultValue = "false") Boolean isCascade){

        if (!isCascade) {
            return workspaceService.deleteWorkspaceById(Long.parseLong(id));
        }
        else
            return workspaceService.deleteCascadeWorkspace(Long.parseLong(id));
    }

    @PostMapping("/{id}/move")
    public Mono<BooleanObject> moveWorkspaceToWorkspace(@PathVariable("id") String id,
                                                  @RequestBody MoveObject body) {
        if (body != null)
            return workspaceService.moveWorkspaceToWorkspace(Long.parseLong(id), body);
        else
            return Mono.error(new IllegalArgumentException());
    }

}

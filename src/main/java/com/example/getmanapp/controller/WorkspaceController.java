package com.example.getmanapp.controller;

import com.example.getmanapp.model.Workspace;
import com.example.getmanapp.service.WorkspaceService;
import com.example.getmanapp.utils.ID;
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

    /**
     * Sample request:
     *
     * for GET workspace/{40}
     *
     * @return
     * {
     *     "id": 40,
     *     "name": "P",
     *     "description": "desc",
     *     "workspace_fk_id": 35,
     *     "requests": [],
     *     "workspaces": []
     * }
     */
    @GetMapping("/{id}")
    public Mono<Workspace> getWorkspaceById(@PathVariable("id") Long id) {

        return workspaceService.getWorkspaceById(id);
    }

    /**
     * Sample request:
     *
     * for PUT request workspace/38
     *
     * @return
     *{
     *     "id": 38,
     *     "name": "Somwhere",
     *     "description": "In America",
     *     "workspace_fk_id": 35,
     *     "requests": null,
     *     "workspaces": null
     * }
     */
    @PutMapping("/{id}")
    public Mono<Workspace> updateWorkspace(@PathVariable("id") Long id,
                                           @RequestBody Workspace workspace) {

        return workspaceService.updateWorkspaceById(id, workspace);
    }

    /**
     * Sample request:
     *
     * for POST workspace/workspace?=40
     *
     * @param workspace_fk_id = 40
     * @param workspace:
     *                 {
     *                      "name": "SandBox",
     *                      "description": "Smells like teen spirit"
     *                  }
     * @return
     *      {
     *     "id": 44,
     *     "parent": 40
     *      }
     */
    @PostMapping()
    public Mono<ID> createWorkspace(@RequestParam(value = "workspace", required = false, defaultValue = "0")
                                        Long workspace_fk_id,
                                    @RequestBody Workspace workspace) {

        workspace.setWorkspace_fk_id(workspace_fk_id);
        return workspaceService.saveWorkspace(workspace);
    }

    /**
     * Sample request:
     *
     * for DELETE workspace/39 or workspace/39?cascade=true
     *
     *
     * @param id = 39
     * @param isCascade = default(false)    / true
     * @return
     *        {
     *          "result": true,
     *          "exceptionMessage": null
     *        }
     */
    @DeleteMapping("/{id}")
    public Mono<BooleanObject> deleteWorkspaceById(@PathVariable("id") Long id,
                                                   @RequestParam(value = "cascade",
                                                                 defaultValue = "false") Boolean isCascade){
        if (!isCascade) {
            return workspaceService.deleteWorkspaceById(id);
        }
        else
            return workspaceService.deleteCascadeWorkspace(id);
    }

    /**
     * Sample request:
     *
     * for POST workspace/38/move
     *
     * @param id = 38
     * @param body = 28
     * @return
     * {
     *     "result": true,
     *     "exceptionMessage": null
     * }
     */
    @PostMapping("/{id}/move")
    public Mono<BooleanObject> moveWorkspaceToWorkspace(@PathVariable("id") Long id,
                                                        @RequestBody MoveObject body) {

        return workspaceService.moveWorkspaceToWorkspace(id, body);
    }

}

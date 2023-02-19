package com.example.getmanapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/version/1/workspace")
public class WorkspaceController {

    @GetMapping("/{id}")
    public String getWorkspaceById(@PathVariable("id") String id) {
        if (Integer.parseInt(id) % 2 == 0)
            return "Id " + id + " is even.";
        else
            return "Id " + id + " is odd.";
    }

    @PutMapping("/{id}")
    public String updateWorkspace(@PathVariable("id") String id,
                                  @RequestBody String body) {
        return body + id;
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

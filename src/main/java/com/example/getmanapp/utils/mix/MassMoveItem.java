package com.example.getmanapp.utils.mix;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MassMoveItem {
    private Long workspace;
    private List<Long> workspaces = new ArrayList<>();
    private List<Long> requests = new ArrayList<>();
}

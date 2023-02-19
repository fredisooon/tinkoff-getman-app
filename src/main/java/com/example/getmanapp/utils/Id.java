package com.example.getmanapp.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
public class Id {

    @Getter
    private Integer id;

    @Getter
    @Setter
    private Integer parent;
}

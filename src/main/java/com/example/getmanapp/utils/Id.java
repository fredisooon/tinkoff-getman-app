package com.example.getmanapp.utils;

import lombok.*;
import lombok.extern.slf4j.Slf4j;


@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class Id{

    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private Long parent;

}

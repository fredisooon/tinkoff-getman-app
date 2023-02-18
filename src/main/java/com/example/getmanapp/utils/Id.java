package com.example.getmanapp.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;

import java.util.function.Consumer;

@NoArgsConstructor
@AllArgsConstructor
public class Id {

    @Getter
    private Integer id;

    @Getter
    @Setter
    private Integer parent;
}

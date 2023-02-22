package com.example.getmanapp.utils;

import org.springframework.http.HttpStatusCode;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
    public static void main(String[] args) {
        //Mono<String> mono = Mono.just("Hello");
//        HttpStatusCode
//        mono.subscribe(
//                data -> System.out.println(data), // onNext
//                err -> System.out.println(err),  // onError
//                () -> System.out.println("Completed!") // onComplete

//        List<Integer> elements = new ArrayList<>();
//        List<Integer> result = new ArrayList<>();
//        Flux.just(1, 2, 3, 4)
//                .log()
//                .subscribe(elements::add,
//                           err -> System.out.println("Error"),
//                           () -> {elements.forEach(element -> result.add(element*2));});
//        System.out.println(elements);
//        System.out.println(result);
    }
}

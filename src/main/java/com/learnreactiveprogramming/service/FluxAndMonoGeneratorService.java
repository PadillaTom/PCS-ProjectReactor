package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        // fromIterable (Should be a DB or RemoteService call)
        return Flux.fromIterable(List.of("Alex", "Ben", "Tom"))
                .log();
    }

    public Mono<String> nameMono() {
        return Mono.just("Alex")
                .log();
    }

    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

        fluxAndMonoGeneratorService.namesFlux()
                .subscribe(name -> System.out.println("Flux Name is: " + name));
        System.out.println("==========");
        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> System.out.println("Mono Name is: " + name));

    }
}

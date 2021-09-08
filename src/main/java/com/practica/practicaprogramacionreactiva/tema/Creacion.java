package com.practica.practicaprogramacionreactiva.tema;

import com.practica.practicaprogramacionreactiva.modelo.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Creacion {

    private static final Logger LOGGER = LoggerFactory.getLogger(Creacion.class);

    public void justFrom() {
        Mono.just(Persona.builder().id(1).nombres("1").edad(1).build());
//        Flux.fromIterable(coleccion);
//        Observable.just(item);
    }

    public void empty() {
        Mono.empty();
        Flux.empty();
        Observable.empty();
    }

    public void range() {
        Flux.range(0, 3)
                .doOnNext(i -> LOGGER.info("i: " + i))
                .subscribe();
    }

    public void repeat() {
        List<Persona> personas = new ArrayList<>();
        personas.add(Persona.builder().id(1).nombres("1").edad(1).build());
        personas.add(Persona.builder().id(2).nombres("2").edad(2).build());
        personas.add(Persona.builder().id(3).nombres("3").edad(3).build());

        Flux.fromIterable(personas)
                .repeat(3)
                .subscribe(p -> LOGGER.info("[repeat flux] " + p.toString()));

//        Mono.just(Persona.builder().id(1).nombres("1").edad(1).build())
//                .repeat(3)
//                .subscribe(p -> LOGGER.info("[repeat mono] " + p.toString()));
    }

}

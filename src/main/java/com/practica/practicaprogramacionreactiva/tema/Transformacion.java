package com.practica.practicaprogramacionreactiva.tema;

import com.practica.practicaprogramacionreactiva.modelo.Persona;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class Transformacion {

    private static final Logger LOGGER = LoggerFactory.getLogger(Transformacion.class);

    public void map() {
//        List<Persona> personas = new ArrayList<>();
//        personas.add(Persona.builder().id(1).nombres("1").edad(1).build());
//        personas.add(Persona.builder().id(2).nombres("2").edad(2).build());
//        personas.add(Persona.builder().id(3).nombres("3").edad(3).build());
//
//        Flux.fromIterable(personas)
//                .map(p -> {
//                    p.setEdad(p.getEdad() + 10);
//                    return p;
//                })
//                .subscribe(p -> LOGGER.info("[map] " + p.toString()));

        Flux<Integer> fx = Flux.range(0, 10);
        Flux<Integer> fx2 = fx.map(x -> x + 10);
        fx2.subscribe(p -> LOGGER.info("[map] " + p));
    }

    public void flatMap() {
        List<Persona> personas = new ArrayList<>();
        personas.add(Persona.builder().id(1).nombres("1").edad(1).build());
        personas.add(Persona.builder().id(2).nombres("2").edad(2).build());
        personas.add(Persona.builder().id(3).nombres("3").edad(3).build());

        Flux.fromIterable(personas)
                .flatMap(p -> {
                    p.setEdad(p.getEdad() + 10);
                    return Mono.just(p);
                })
                .subscribe(p -> LOGGER.info("[flatMap] " + p.toString()));
    }

    public void groupBy() {
        List<Persona> personas = new ArrayList<>();
        personas.add(Persona.builder().id(1).nombres("1").edad(1).build());
        personas.add(Persona.builder().id(1).nombres("2").edad(2).build());
        personas.add(Persona.builder().id(3).nombres("3").edad(3).build());

        Flux.fromIterable(personas)
                .groupBy(Persona::getId)
                .flatMap(id -> id.collectList())
                .subscribe(p -> LOGGER.info("[groupBy] " + p.toString()));
    }

}

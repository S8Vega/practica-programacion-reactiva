package com.practica.practicaprogramacionreactiva;

import com.practica.practicaprogramacionreactiva.modelo.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class PracticaProgramacionReactivaApplication implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PracticaProgramacionReactivaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PracticaProgramacionReactivaApplication.class, args);
	}

	public void reactor() {
		Mono
				.just(
						Persona.builder()
								.id(1).nombres("sebas").edad(23)
								.build()
				)
				.doOnNext(p -> LOGGER.info("[Reactor] " + p))
				.subscribe(p -> LOGGER.info("[Reactor] " + p));
	}

	public void rxjava2() {
		Observable
				.just(
						Persona.builder()
								.id(1).nombres("sebas").edad(23)
								.build()
				)
				.doOnNext(p -> LOGGER.info("[rxjava2] " + p))
				.subscribe(p -> LOGGER.info("[rxjava2] " + p));
	}

	public void mono() {
		Mono
				.just(
						Persona.builder()
								.id(1).nombres("sebas").edad(23)
								.build()
				)
				.subscribe(p -> LOGGER.info("[mono]  " + p));
	}

	public void flux() {
		List<Persona> personas = new ArrayList<>();
		personas.add(Persona.builder().id(1).nombres("1").edad(1).build());
		personas.add(Persona.builder().id(2).nombres("2").edad(2).build());
		personas.add(Persona.builder().id(3).nombres("3").edad(3).build());

		Flux.fromIterable(personas).subscribe(p -> LOGGER.info("[flux] " + p.toString()));
	}

	public void fluxMono() {
		List<Persona> personas = new ArrayList<>();
		personas.add(Persona.builder().id(1).nombres("1").edad(1).build());
		personas.add(Persona.builder().id(2).nombres("2").edad(2).build());
		personas.add(Persona.builder().id(3).nombres("3").edad(3).build());

		Flux<Persona> fx = Flux.fromIterable(personas);
		fx.collectList().subscribe(p -> LOGGER.info("[flux] " + p.toString()));
	}

	@Override
	public void run(String... args) throws Exception {
		mono();
		flux();
		fluxMono();
	}
}

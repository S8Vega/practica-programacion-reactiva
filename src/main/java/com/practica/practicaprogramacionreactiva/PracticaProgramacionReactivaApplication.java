package com.practica.practicaprogramacionreactiva;

import com.practica.practicaprogramacionreactiva.modelo.Persona;
import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

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
				.doOnNext(p -> LOGGER.info("[Reactor] Persona: " + p))
				.subscribe(p -> LOGGER.info("[Reactor] Persona: " + p));
	}

	public void rxjava2() {
		Observable
				.just(
						Persona.builder()
								.id(1).nombres("sebas").edad(23)
								.build()
				)
				.doOnNext(p -> LOGGER.info("[rxjava2] Persona: " + p))
				.subscribe(p -> LOGGER.info("[rxjava2] Persona: " + p));
	}

	@Override
	public void run(String... args) throws Exception {
		reactor();
		rxjava2();
	}
}

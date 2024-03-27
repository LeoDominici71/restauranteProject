package com.restaurant.springboot.fiapProject.performance;

import static io.gatling.javaapi.core.CoreDsl.StringBody;
import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import java.time.Duration;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

public class performanceSimulation extends Simulation {

	private final HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080").header("Content-Type",
			"application/json");

	ActionBuilder adicinarRestaurante = http("adicionar restaurante").post("/restaurantes").body(StringBody(
			"{\"nome\":\"Nayumi\",\"endereco\":\"endereco\",\"cidade\":\"cidade\",\"gastronomia\":\"gastronomia\",\"horarioAbertura\":\"18:00\",\"horarioFechamento\":\"22:00\",\"mesasDisponiveis\":\"10\"}"))
			.check(status().is(201));

	ScenarioBuilder cenarioRegistrarRestaurante = scenario("Registrar restaurante").exec(adicinarRestaurante);

	ActionBuilder adicinarReserva = http("adicionar reserva").post("/reserva").body(StringBody(
			"{\"nomeUsuario\":\"Osvaldo\",\"telefone\":\"13 981549851\",\"email\":\"leonardodominici@gmail.com\",\"data\":\"16/03/2024\",\"hora\":\"13:00\",\"quantidadePessoas\":\"4\",\"restauranteNome\":\"nayumi\"}"))
			.check(status().is(201));

	ScenarioBuilder cenarioRegistrarReserva = scenario("Registrar reserva").exec(adicinarRestaurante)
			.exec(adicinarReserva);

	ActionBuilder adicinarComentario = http("adicionar comentario").post("/comentario").body(StringBody(
			"{\"nomeCliente\":\"Osvaldo\",\"restauranteNome\":\"nayumi\",\"restauranteId\":\"1\",\"comentario\":\"Comida otima\",\"avaliacao\":\"10\",\"reservaId\":\"2\"}"))
			.check(status().is(201));

	ScenarioBuilder cenarioRegistrarComentario = scenario("Registrar reserva").exec(adicinarRestaurante)
			.exec(adicinarReserva).exec(adicinarComentario);

	{
		setUp(cenarioRegistrarRestaurante.injectOpen(rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
				constantUsersPerSec(10).during(Duration.ofSeconds(60)),
				rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10))),

				cenarioRegistrarReserva.injectOpen(rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
						constantUsersPerSec(10).during(Duration.ofSeconds(60)),
						rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10))),
				cenarioRegistrarComentario.injectOpen(rampUsersPerSec(1).to(10).during(Duration.ofSeconds(10)),
						constantUsersPerSec(10).during(Duration.ofSeconds(60)),
						rampUsersPerSec(10).to(1).during(Duration.ofSeconds(10))))

				.protocols(httpProtocol).assertions(global().responseTime().max().lt(50));
	}

}

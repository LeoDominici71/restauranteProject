package com.restaurant.springboot.fiapProject.adapter.input;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaAtualizaRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaResponse;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AtualizaReserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.BuscaReservaPorId;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.DeletarReserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaDeReservaPorDiaHora;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaReservaPorRestaurante;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.RegistroReserva;
import com.restaurant.springboot.fiapProject.utils.ApplicationUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/reserva")
public class ReservaControler {

	private final BuscaReservaPorId reservaPorId;
	private final DeletarReserva deletarReserva;
	private final RegistroReserva registroReserva;
	private final AtualizaReserva atualizaReserva;
	private final ListaDeReservaPorDiaHora listaDeReservaPorDiaHora;
	private final ListaReservaPorRestaurante listaReservaPorRestaurante;

	@Autowired
	public ReservaControler(BuscaReservaPorId reservaPorId, DeletarReserva deletarReserva,
			RegistroReserva registroReserva, AtualizaReserva atualizaReserva,
			ListaDeReservaPorDiaHora listaDeReservaPorDiaHora, ListaReservaPorRestaurante listaReservaPorRestaurante) {
		this.reservaPorId = reservaPorId;
		this.deletarReserva = deletarReserva;
		this.registroReserva = registroReserva;
		this.atualizaReserva = atualizaReserva;
		this.listaDeReservaPorDiaHora = listaDeReservaPorDiaHora;
		this.listaReservaPorRestaurante = listaReservaPorRestaurante;
	}

	@Operation(description = "Deve registrar uma Reserva")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Reserva criada"),
			@ApiResponse(responseCode = "404", description = "Reserva inexistente"),
			@ApiResponse(responseCode = "400", description = "Parametros nulos")

	})
	@PostMapping
	public ResponseEntity<ReservaResponse> registroReserva(@RequestBody ReservaRequest request) {
		Reserva reserva = registroReserva.registroReserva(ApplicationUtils.toReserva(request));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(reserva.getReservaId())
				.toUri();
		return ResponseEntity.created(uri).body(new ReservaResponse(reserva));
	}

	@Operation(description = "Deve buscar um reserva por id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "reserva encontrada"),
			@ApiResponse(responseCode = "404", description = "reserva inexistente"),

	})
	@GetMapping(value = "/{reservaId}")
	public ResponseEntity<ReservaResponse> buscaReservaPorId(@PathVariable Long reservaId) {
		Reserva reserva = reservaPorId.buscarReservaPorId(reservaId);
		return ResponseEntity.ok().body(new ReservaResponse(reserva));
	}

	@Operation(description = "Deve deletar uma reserva por id")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "reserva encontrada"),
			@ApiResponse(responseCode = "404", description = "reserva inexistente"),

	})
	@DeleteMapping(value = "/manager/{reservaId}")
	public ResponseEntity<Void> deletarReserva(@PathVariable Long reservaId) {
		deletarReserva.deletarReserva(reservaId);
		return ResponseEntity.noContent().build();
	}

	@Operation(description = "Deve atualizar uma reserva por id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "reserva atualizada"),
			@ApiResponse(responseCode = "404", description = "reserva inexistente"),

	})
	@PutMapping(value = "/atualiza/{reservaId}")
	public ResponseEntity<ReservaResponse> atualizarReserva(@PathVariable Long reservaId,
			@RequestBody ReservaAtualizaRequest request) {
		Reserva reserva = atualizaReserva.atualizaReserva(reservaId, ApplicationUtils.toReserva(request));
		return ResponseEntity.ok().body(new ReservaResponse(reserva));

	}
	@Operation(description = "Deve buscar uma lista de reserva por dia, hora e restaurante id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "reservas encontrada")

	})
	@GetMapping(value = "/porDiaHora")
	public ResponseEntity<List<ReservaResponse>> buscaListaReservaPorDiaHora(@RequestParam String restauranteId,
			@RequestParam String date, @RequestParam String time) {
		List<Reserva> listaReserva = listaDeReservaPorDiaHora.listaReservaPorDiaHora(
				ApplicationUtils.parseLong(restauranteId), ApplicationUtils.localDateFormatter(date),
				ApplicationUtils.localTimeFormatter(time));
		List<ReservaResponse> reservaResponse = listaReserva.stream().map(l -> new ReservaResponse(l))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(reservaResponse);
	}
	@Operation(description = "Deve buscar uma lista de reserva por restaurante id")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "reservas encontrada")

	})
	@GetMapping(value = "/restaurante/{restauranteId}")
	public ResponseEntity<List<ReservaResponse>> buscaListaReservaPorRestaurante(@PathVariable Long restauranteId) {
		List<Reserva> listaReserva = listaReservaPorRestaurante.listaReservaPorRestaurante(restauranteId);
		List<ReservaResponse> reservaResponse = listaReserva.stream().map(l -> new ReservaResponse(l))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(reservaResponse);
	}

}

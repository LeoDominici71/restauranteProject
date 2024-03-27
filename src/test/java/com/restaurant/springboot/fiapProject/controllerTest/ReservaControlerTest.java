package com.restaurant.springboot.fiapProject.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.springboot.fiapProject.adapter.input.ReservaControler;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaRequest;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AtualizaReserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.BuscaReservaPorId;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.DeletarReserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaDeReservaPorDiaHora;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaReservaPorRestaurante;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.RegistroReserva;
import com.restaurant.springboot.fiapProject.factory.Factory;

@WebMvcTest(ReservaControler.class)
public class ReservaControlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BuscaReservaPorId buscarReservaPorId;

	@MockBean
	private DeletarReserva deletarReserva;

	@MockBean
	private RegistroReserva registroReserva;

	@MockBean
	private AtualizaReserva atualizaReserva;

	@MockBean
	private ListaDeReservaPorDiaHora listaDeReservaPorDiaHora;

	@MockBean
	private ListaReservaPorRestaurante listaReservaPorRestaurante;

	@Test
	public void deveRegistrarUmaReserva() throws Exception {
		// Arrange
		ReservaRequest request = Factory.createReservaRequest();
		Reserva reserva = Factory.createReserva();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		when(registroReserva.registroReserva(any())).thenReturn(reserva);
		ResultActions response = mockMvc
				.perform(post("/reserva").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.nomeUsuario").exists());

	}

	@Test
	public void deveBuscarReservaPorId() throws Exception {
		// Arrange
		Reserva reserva = Factory.createReserva();

		// Act
		when(buscarReservaPorId.buscarReservaPorId(1L)).thenReturn(reserva);
		ResultActions result = mockMvc.perform(get("/reserva/{reservaId}", 1L).accept(MediaType.APPLICATION_JSON));

		// Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.nomeUsuario").exists());

	}

	@Test
	public void deveDeletarReservaPorId() throws Exception {
		// Arrange

		doNothing().when(deletarReserva).deletarReserva(1L);
		// Act
		ResultActions result = mockMvc
				.perform(delete("/reserva/manager/{reservaId}", 1L).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isNoContent());
	}

	@Test
	public void deveAtualizarReservaPorId() throws Exception {
		// Arrange
		ReservaRequest request = Factory.createReservaRequest();
		String jsonBody = objectMapper.writeValueAsString(request);
		Reserva reserva = Factory.createReservaComReservaId();
		when(atualizaReserva.atualizaReserva(eq(1L), any())).thenReturn(reserva);
		// Act
		ResultActions result = mockMvc.perform(put("/reserva/atualiza/{reservaId}", 1L).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.nomeUsuario").exists());

	}

	@Test
	public void deveBuscarListaReservaPorDiaHora() throws Exception {
		// Arrange
		String restauranteId = "1";
		String date = "24/03/2024";
		String time = "18:30";
		Reserva reserva = Factory.createReservaComId();
		Reserva reserva1 = Factory.createReservaComId();
		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva);
		reservas.add(reserva1);
		when(listaDeReservaPorDiaHora.listaReservaPorDiaHora(1L, LocalDate.of(2024, 03, 24), LocalTime.of(18, 30)))
				.thenReturn(reservas);

		// Act
		ResultActions response = mockMvc.perform(get("/reserva/porDiaHora").param("restauranteId", restauranteId)
				.param("date", date).param("time", time).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$[0].nomeUsuario").value(reservas.get(0).getNomeUsuario()));
		response.andExpect(jsonPath("$[1].nomeUsuario").value(reservas.get(1).getNomeUsuario()));

	}

	@Test
	public void deveBuscarListaReservaPorRestaurante() throws Exception {
		// Arrange
		Reserva reserva = Factory.createReservaComId();
		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva);
		// Act
		when(listaReservaPorRestaurante.listaReservaPorRestaurante(reserva.getRestauranteId())).thenReturn(reservas);
		ResultActions result = mockMvc
				.perform(get("/reserva/restaurante/{restauranteId}", 1L).accept(MediaType.APPLICATION_JSON));
		
		//Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].nomeUsuario").value(reservas.get(0).getNomeUsuario()));

	}

}

package com.restaurant.springboot.fiapProject.controllerIntegration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaRequest;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ReservaControlerIT {
	
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	
	@Autowired
	private JpaReservaRepository reservaRepository;

	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveRegistrarUmaReserva() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		ReservaRequest request = Factory.createReservaRequestComId();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/reserva").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.nomeUsuario").exists());
		response.andExpect(jsonPath("$.telefone").exists());
		response.andExpect(jsonPath("$.email").exists());
		response.andExpect(jsonPath("$.data").exists());
		response.andExpect(jsonPath("$.hora").exists());
		response.andExpect(jsonPath("$.quantidadePessoas").exists());


	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarUmaReservaQuandoNomeRestauranteNaoExiste() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		ReservaRequest request = Factory.createReservaRequestComNomeIncorreto();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/reserva").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isNotFound());

	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarUmaReservaQuandoIdRestauranteNaoExiste() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		ReservaRequest request = Factory.createReservaRequestComIdIncorreto();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/reserva").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isNotFound());

	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveRegistrarUmaReservaProcurandoRestaurantePorNome() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		ReservaRequest request = Factory.createReservaRequestComNome();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/reserva").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.nomeUsuario").exists());
		response.andExpect(jsonPath("$.telefone").exists());
		response.andExpect(jsonPath("$.email").exists());
		response.andExpect(jsonPath("$.data").exists());
		response.andExpect(jsonPath("$.hora").exists());
		response.andExpect(jsonPath("$.quantidadePessoas").exists());

	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarUmaReservaQuandoAQuantidadeDePessoasEMaiorQueAMaxima() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		ReservaRequest request = Factory.createReservaRequestComIdMaiorQueOMaximo();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/reserva").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isBadRequest());

	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarUmaReservaQuandoHouverValoresNulos() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		ReservaRequest request = Factory.createReservaRequestComValoresNulos();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/reserva").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isBadRequest());

	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveBuscarReservaPorId() throws Exception {

		//Arrange
		reservaRepository.save(Factory.createReservaEntity());
		
		// Act
		ResultActions result = mockMvc.perform(get("/reserva/{reservaId}", 1L).accept(MediaType.APPLICATION_JSON));

		// Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.nomeUsuario").exists());
		result.andExpect(jsonPath("$.telefone").exists());
		result.andExpect(jsonPath("$.email").exists());
		result.andExpect(jsonPath("$.data").exists());
		result.andExpect(jsonPath("$.hora").exists());
		result.andExpect(jsonPath("$.quantidadePessoas").exists());

	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveBuscarReservaPorIdQuandoIdNaoExiste() throws Exception {
		
		// Act
		ResultActions result = mockMvc.perform(get("/reserva/{reservaId}", 1L).accept(MediaType.APPLICATION_JSON));

		// Assert
		result.andExpect(status().isNotFound());

	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveDeletarReservaPorId() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaCanceladaEntity());

		// Act
		ResultActions result = mockMvc
				.perform(delete("/reserva/manager/{reservaId}", 1L).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isNoContent());
	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveDeletarReservaPorIdQuandoNaoFOoiMovidoOStatus() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());

		// Act
		ResultActions result = mockMvc
				.perform(delete("/reserva/manager/{reservaId}", 1L).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isBadRequest());
	}
	
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveDeletarReservaPorIdQuandoIdNaoExiste() throws Exception {
		
		// Act
		ResultActions result = mockMvc
				.perform(delete("/reserva/manager/{reservaId}", 1L).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isNotFound());
	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveAtualizarReservaPorId() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntityDataAmanha());
		ReservaRequest request = Factory.createReservaRequest();
		String jsonBody = objectMapper.writeValueAsString(request);
		// Act
		ResultActions result = mockMvc.perform(put("/reserva/atualiza/{reservaId}", 1L).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.nomeUsuario").exists());
		result.andExpect(jsonPath("$.telefone").exists());
		result.andExpect(jsonPath("$.email").exists());
		result.andExpect(jsonPath("$.data").exists());
		result.andExpect(jsonPath("$.hora").exists());
		result.andExpect(jsonPath("$.quantidadePessoas").exists());

	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveAtualizarReservaQuandoOIdNaoExiste() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntityDataAmanha());
		ReservaRequest request = Factory.createReservaRequest();
		String jsonBody = objectMapper.writeValueAsString(request);
		// Act
		ResultActions result = mockMvc.perform(put("/reserva/atualiza/{reservaId}", 3L).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isNotFound());


	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveBuscarListaReservaPorDiaHora() throws Exception {
		// Arrange
		String nome = "Osvaldo";
		reservaRepository.save(Factory.createReservaEntityDataAmanha());
		String restauranteId = "1";
		String date = "22/03/2024";
		String time = "18:30";

		// Act
		ResultActions response = mockMvc.perform(get("/reserva/porDiaHora").param("restauranteId", restauranteId)
				.param("date", date).param("time", time).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$[0].nomeUsuario").value(nome));
		response.andExpect(jsonPath("$[0].telefone").exists());
		response.andExpect(jsonPath("$[0].email").exists());
		response.andExpect(jsonPath("$[0].data").exists());
		response.andExpect(jsonPath("$[0].hora").exists());
		response.andExpect(jsonPath("$[0].quantidadePessoas").exists());


	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveBuscarListaReservaPorRestaurante() throws Exception {
		// Arrange
		reservaRepository.save(Factory.createReservaEntityDataAmanha());
		Reserva reserva = Factory.createReservaComId();
		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva);
		// Act
		ResultActions result = mockMvc
				.perform(get("/reserva/restaurante/{restauranteId}", 1L).accept(MediaType.APPLICATION_JSON));
		
		//Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$[0].nomeUsuario").exists());
		result.andExpect(jsonPath("$[0].telefone").exists());
		result.andExpect(jsonPath("$[0].email").exists());
		result.andExpect(jsonPath("$[0].data").exists());
		result.andExpect(jsonPath("$[0].hora").exists());
		result.andExpect(jsonPath("$[0].quantidadePessoas").exists());
	}
	
	

}

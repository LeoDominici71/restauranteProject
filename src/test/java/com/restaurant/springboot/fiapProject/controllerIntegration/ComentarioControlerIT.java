package com.restaurant.springboot.fiapProject.controllerIntegration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.restaurant.springboot.fiapProject.adapter.input.entities.ComentarioRequest;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaComentarioRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ComentarioEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class ComentarioControlerIT {
	

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private JpaComentarioRepository repository;
	
	@Autowired
	private JpaReservaRepository reservaRepository;
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveRegistrarComentario() throws Exception {
		// Arrange
		ComentarioRequest request = Factory.createComentarioRequest();
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/comentario").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.comentario").exists());
		response.andExpect(jsonPath("$.avaliacao").exists());
		response.andExpect(jsonPath("$.nomeCliente").exists());


	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarComentarioQuandoHaValoresNulos() throws Exception {
		// Arrange
		ComentarioRequest request = Factory.createComentarioRequestComValoresNulos();
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/comentario").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isBadRequest());
	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarComentarioQuandoReservaIdNaoExiste() throws Exception {
		// Arrange
		ComentarioRequest request = Factory.createComentarioRequestComIdReservaInexistente();
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/comentario").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isNotFound());
	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveRegistrarComentarioQuandoAReservaFoiCancelada() throws Exception {
		// Arrange
		ComentarioRequest request = Factory.createComentarioRequest();
		ReservaEntity reserva = Factory.createReservaCanceladaEntity();
		reservaRepository.save(reserva);
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/comentario").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isBadRequest());
	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveBuscarComentarioPorId() throws Exception {
		// Arrange
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);
		// Act
		ResultActions result = mockMvc.perform(get("/comentario/{comentarioId}", 1L).accept(MediaType.APPLICATION_JSON));

		// Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.comentario").exists());
		result.andExpect(jsonPath("$.avaliacao").exists());
		result.andExpect(jsonPath("$.nomeCliente").exists());

	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveBuscarComentarioQuandoIdNaoExiste() throws Exception {
		// Arrange
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);
		// Act
		ResultActions result = mockMvc.perform(get("/comentario/{comentarioId}", 3L).accept(MediaType.APPLICATION_JSON));

		// Assert
		result.andExpect(status().isNotFound());


	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveDeletarReservaPorId() throws Exception {
		// Arrange
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);
		// Act
		ResultActions result = mockMvc
				.perform(delete("/comentario/{comentarioId}", 1L).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isNoContent());
	}
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void naoDeveDeletarReservaQuandoIdNaoExiste() throws Exception {
		// Arrange
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);
		// Act
		ResultActions result = mockMvc
				.perform(delete("/comentario/{comentarioId}", 3L).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isNotFound());
	}
	


}

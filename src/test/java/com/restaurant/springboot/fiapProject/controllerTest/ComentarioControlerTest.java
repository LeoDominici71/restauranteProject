package com.restaurant.springboot.fiapProject.controllerTest;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.springboot.fiapProject.adapter.input.ComentarioControler;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ComentarioRequest;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.BuscarComentarioPorId;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.DeletarComentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.RegistroComentario;
import com.restaurant.springboot.fiapProject.factory.Factory;

@WebMvcTest(ComentarioControler.class)
public class ComentarioControlerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private BuscarComentarioPorId buscarComentarioPorId;

	@MockBean
	private DeletarComentario deletarComentario;

	@MockBean
	private RegistroComentario registroComentario;

	@Test
	public void deveRegistarComentario() throws Exception {
		// Arrange
		ComentarioRequest request = Factory.createComentarioRequest();
		Comentario comentario = Factory.createComentario();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		when(registroComentario.registroComentario(comentario)).thenReturn(comentario);
		ResultActions response = mockMvc
				.perform(post("/comentario").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.comentario").exists());
	}
	
	@Test
	public void deveBuscarComentarioPorId() throws Exception {
		// Arrange
		Comentario comentario = Factory.createComentario();
		// Act
		when(buscarComentarioPorId.buscarComentarioPorId(1L)).thenReturn(comentario);
		ResultActions result = mockMvc.perform(get("/comentario/{comentarioId}", 1L).accept(MediaType.APPLICATION_JSON));

		// Assert
		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.comentario").exists());

	}
	
	@Test
	public void deveDeletarReservaPorId() throws Exception {
		// Arrange

		doNothing().when(deletarComentario).deletarComentario(1L);
		// Act
		ResultActions result = mockMvc
				.perform(delete("/comentario/{comentarioId}", 1L).accept(MediaType.APPLICATION_JSON));
		// Assert
		result.andExpect(status().isNoContent());
	}
	
	

}

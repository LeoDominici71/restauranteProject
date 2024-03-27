package com.restaurant.springboot.fiapProject.controllerTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.error.ShouldHaveSameSizeAs;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.springboot.fiapProject.adapter.input.RestauranteControler;
import com.restaurant.springboot.fiapProject.adapter.input.entities.RestauranteRequest;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.BuscarRestaurantes;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.CadastroRestaurantes;
import com.restaurant.springboot.fiapProject.factory.Factory;

@WebMvcTest(RestauranteControler.class)
public class RestauranteControlerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CadastroRestaurantes cadastroRestaurante;

	@MockBean
	private BuscarRestaurantes buscaRestaurantes;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	public void deveriaSalvarUmRestauranteeRetornarCriado() throws Exception {
		// Arrange
		RestauranteRequest request = Factory.createRestauranteRequest();
		Restaurante restaurante = Factory.createRestaurante();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		when(cadastroRestaurante.registroRestaurantes(any())).thenReturn(restaurante);
		ResultActions response = mockMvc.perform(post("/restaurantes").content(jsonBody).contentType(MediaType.APPLICATION_JSON));
		
		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.nome").exists());


	}
	
	@Test
	public void deveriaEncontrarUmRestaurantePorNome() throws Exception {
		//Arrange
		String nome = "Nayumi";
		Restaurante restaurante1 = Factory.createRestaurante();
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);
        when(buscaRestaurantes.buscarRestaurantePorNome(nome)).thenReturn(restaurantes);
        
        //Act
        ResultActions response = mockMvc.perform(get("/restaurantes/buscaPorNome")
                .param("nome", nome)
                .contentType(MediaType.APPLICATION_JSON));

		//Assert
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$[0].nome").value(restaurante1.getNome()));

		
	}
	
	@Test
	public void deveriaEncontrarUmRestaurantePorCidade() throws Exception {
		//Arrange
		String cidade = "Praia Grande";
		Restaurante restaurante1 = Factory.createRestaurante();
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);
        when(buscaRestaurantes.buscarRestaurantePorCidade(cidade)).thenReturn(restaurantes);
        
        //Act
        ResultActions response = mockMvc.perform(get("/restaurantes/buscaPorCidade")
                .param("cidade", cidade)
                .contentType(MediaType.APPLICATION_JSON));

		//Assert
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$[0].cidade").value(restaurante1.getCidade()));

		
	}
	
	@Test
	public void deveriaEncontrarUmRestaurantePorCozinha() throws Exception {
		//Arrange
		String gastronomia = "Japonesa";
		Restaurante restaurante1 = Factory.createRestaurante();
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);
        when(buscaRestaurantes.buscarRestaurantePorCozinha(gastronomia)).thenReturn(restaurantes);
        
        //Act
        ResultActions response = mockMvc.perform(get("/restaurantes/buscaPorCozinha")
                .param("gastronomia", gastronomia)
                .contentType(MediaType.APPLICATION_JSON));

		//Assert
        response.andExpect(status().isOk());
        response.andExpect(jsonPath("$[0].gastronomia").value(restaurante1.getGastronomia()));

		
	}

}

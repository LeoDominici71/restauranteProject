package com.restaurant.springboot.fiapProject.controllerIntegration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.springboot.fiapProject.adapter.input.entities.RestauranteRequest;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class RestauranteControlerIT {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private JpaRestauranteRepository repository;

	@BeforeEach
	public void setup() {
		// Inserindo dados de teste no banco de dados
		repository.save(Factory.createRestauranteEntity());
	}

	@Test
	public void deveriaSalvarUmRestauranteeRetornarCriado() throws Exception {
		// Arrange
		RestauranteRequest request = Factory.createRestauranteRequest();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/restaurantes").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isCreated());
		response.andExpect(jsonPath("$.nome").exists());
		response.andExpect(jsonPath("$.endereco").exists());
		response.andExpect(jsonPath("$.cidade").exists());
		response.andExpect(jsonPath("$.gastronomia").exists());
		response.andExpect(jsonPath("$.horarioAbertura").exists());
		response.andExpect(jsonPath("$.horarioFechamento").exists());
		response.andExpect(jsonPath("$.mesasDisponiveis").exists());




	}
	
	@Test
	public void naoDeveriaSalvarUmRestauranteeRetornarCriadoQuandoAlgumParametroForNulo() throws Exception {
		// Arrange
		RestauranteRequest request = Factory.createRestauranteRequestComAlgumParametroNulo();
		String jsonBody = objectMapper.writeValueAsString(request);

		// Act
		ResultActions response = mockMvc
				.perform(post("/restaurantes").content(jsonBody).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isBadRequest());

	}

	@Test
	public void deveriaEncontrarUmRestaurantePorNome() throws Exception {
		// Arrange
		String nome = "Nayumi";
		Restaurante restaurante1 = Factory.createRestaurante();
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);

		// Act
		ResultActions response = mockMvc
				.perform(get("/restaurantes/buscaPorNome").param("nome", nome).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$[0].nome").exists());
		response.andExpect(jsonPath("$[0].endereco").exists());
		response.andExpect(jsonPath("$[0].cidade").exists());
		response.andExpect(jsonPath("$[0].gastronomia").exists());
		response.andExpect(jsonPath("$[0].horarioAbertura").exists());
		response.andExpect(jsonPath("$[0].horarioFechamento").exists());
		response.andExpect(jsonPath("$[0].mesasDisponiveis").exists());
	}

	@Test
	public void deveriaEncontrarUmRestaurantePorCidade() throws Exception {
		// Arrange
		String cidade = "Praia Grande";
		Restaurante restaurante1 = Factory.createRestaurante();
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);

		// Act
		ResultActions response = mockMvc.perform(
				get("/restaurantes/buscaPorCidade").param("cidade", cidade).contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$[0].nome").exists());
		response.andExpect(jsonPath("$[0].endereco").exists());
		response.andExpect(jsonPath("$[0].cidade").exists());
		response.andExpect(jsonPath("$[0].gastronomia").exists());
		response.andExpect(jsonPath("$[0].horarioAbertura").exists());
		response.andExpect(jsonPath("$[0].horarioFechamento").exists());
		response.andExpect(jsonPath("$[0].mesasDisponiveis").exists());
	}

	@Test
	public void deveriaEncontrarUmRestaurantePorCozinha() throws Exception {
		// Arrange
		String gastronomia = "Japonesa";
		Restaurante restaurante1 = Factory.createRestaurante();
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);

		// Act
		ResultActions response = mockMvc.perform(get("/restaurantes/buscaPorCozinha").param("gastronomia", gastronomia)
				.contentType(MediaType.APPLICATION_JSON));

		// Assert
		response.andExpect(status().isOk());
		response.andExpect(jsonPath("$[0].nome").exists());
		response.andExpect(jsonPath("$[0].endereco").exists());
		response.andExpect(jsonPath("$[0].cidade").exists());
		response.andExpect(jsonPath("$[0].gastronomia").exists());
		response.andExpect(jsonPath("$[0].horarioAbertura").exists());
		response.andExpect(jsonPath("$[0].horarioFechamento").exists());
		response.andExpect(jsonPath("$[0].mesasDisponiveis").exists());
	}

}
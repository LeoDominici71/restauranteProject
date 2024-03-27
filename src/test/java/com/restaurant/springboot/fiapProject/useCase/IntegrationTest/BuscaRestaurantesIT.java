package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.BuscarRestaurantes;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class BuscaRestaurantesIT {

	@Autowired
	private JpaRestauranteRepository repository;

	@Autowired
	private BuscarRestaurantes buscarRestaurantes;

	@BeforeEach
	public void setup() {
		// Inserindo dados de teste no banco de dados
		repository.save(Factory.createRestauranteEntity());
	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void buscarestaurantesPorNome() {

		// Arrange
		String nome = "Nayumi";

		// Act
		List<Restaurante> restaurantesSaved = buscarRestaurantes.buscarRestaurantePorNome(nome);

		// Assert
		Assertions.assertNotNull(restaurantesSaved);
		Assertions.assertNotNull(restaurantesSaved.get(0).getId());

	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void buscarRestaurantesPorCozinha() {

		// Arrange
		String cozinha = "Japonesa";
		// Act
		List<Restaurante> restaurantesSaved = buscarRestaurantes.buscarRestaurantePorCozinha(cozinha);

		// Assert
		Assertions.assertNotNull(restaurantesSaved);
		Assertions.assertNotNull(restaurantesSaved.get(0).getId());

	}

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void buscarRestaurantesPorCidade() {

		// Arrange
		String cidade = "Praia Grande";

		// Act
		List<Restaurante> restaurantesSaved = buscarRestaurantes.buscarRestaurantePorCidade(cidade);

		// Assert
		Assertions.assertNotNull(restaurantesSaved);
		Assertions.assertNotNull(restaurantesSaved.get(0).getId());

	}

}

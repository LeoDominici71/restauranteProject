package com.restaurant.springboot.fiapProject.useCase;

import static org.mockito.Mockito.CALLS_REAL_METHODS;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.core.RestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.BuscarRestaurantes;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class BuscarRestaurantesTest {

	@Mock
	private RestauranteRepository repository;

	@InjectMocks
	private BuscarRestaurantes buscarRestaurantes;

	@Test
	public void buscarestaurantesPorNome() {

		// Arrange
		Restaurante restaurante1 = Factory.createRestaurante();
		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);
		Mockito.when(repository.buscarRestaurantePorNome(restaurante1.getNome())).thenReturn(restaurantes);

		// Act
		List<Restaurante> restaurantesSaved = buscarRestaurantes.buscarRestaurantePorNome(restaurante1.getNome());

		// Assert
		Assertions.assertNotNull(restaurantesSaved);
		Assertions.assertEquals(restaurante1, restaurantesSaved.get(0));

	}

	@Test
	public void buscarRestaurantesPorCozinha() {

		// Arrange
		String cozinha = "Japonesa";
		Restaurante restaurante1 = Factory.createRestaurante();
		Restaurante restaurante2 = Factory.createRestaurante();

		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);
		restaurantes.add(restaurante2);

		Mockito.when(repository.buscarRestaurantePorCozinha(cozinha)).thenReturn(restaurantes);

		// Act
		List<Restaurante> restaurantesSaved = buscarRestaurantes.buscarRestaurantePorCozinha(cozinha);

		// Assert
		Assertions.assertNotNull(restaurantesSaved);
		Assertions.assertEquals(restaurante1, restaurantesSaved.get(0));
		Assertions.assertEquals(restaurante2, restaurantesSaved.get(1));


	}
	
	
	@Test
	public void buscarRestaurantesPorCidade() {

		// Arrange
		String cidade = "Praia Grande";
		Restaurante restaurante1 = Factory.createRestaurante();
		Restaurante restaurante2 = Factory.createRestaurante();

		List<Restaurante> restaurantes = new ArrayList<>();
		restaurantes.add(restaurante1);
		restaurantes.add(restaurante2);

		Mockito.when(repository.buscarRestaurantePorCidade(cidade)).thenReturn(restaurantes);

		// Act
		List<Restaurante> restaurantesSaved = buscarRestaurantes.buscarRestaurantePorCidade(cidade);

		// Assert
		Assertions.assertNotNull(restaurantesSaved);
		Assertions.assertEquals(restaurante1, restaurantesSaved.get(0));
		Assertions.assertEquals(restaurante2, restaurantesSaved.get(1));


	}

}

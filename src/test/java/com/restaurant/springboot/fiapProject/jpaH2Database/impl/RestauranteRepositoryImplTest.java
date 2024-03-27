package com.restaurant.springboot.fiapProject.jpaH2Database.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.impl.RestauranteRepositoryImpl;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class RestauranteRepositoryImplTest {

	@Mock
	private JpaRestauranteRepository repository;

	@InjectMocks
	private RestauranteRepositoryImpl restauranteRepository;

	@Test
	public void deveRegistrarRestaurante() {

		// Arrange
		RestauranteEntity restauranteEntity = Factory.createRestauranteEntity();
		Restaurante restaurante = Factory.createRestaurante();
		Mockito.when(repository.save(Mockito.any(RestauranteEntity.class))).thenReturn(restauranteEntity);
		// Act
		Restaurante restauranteSalvo = restauranteRepository.registroRestaurantes(restaurante);

		// Assert
		Assertions.assertNotNull(restauranteSalvo);
		Assertions.assertEquals(restauranteSalvo.getNome(), restauranteEntity.getNome());

	}
	
	@Test
	public void deveBuscarRestaurantePorNome() {
		
		//Arrange
		
		String nome = "Nayumi";
		
		RestauranteEntity restauranteEntity1 = Factory.createRestauranteEntity();
		RestauranteEntity restauranteEntity2 = Factory.createRestauranteEntity();

		List<RestauranteEntity> listaRestauranteEntity = new ArrayList<>();
		
		listaRestauranteEntity.add(restauranteEntity1);
		listaRestauranteEntity.add(restauranteEntity2);
		
		Mockito.when(repository.buscarPorNome(nome)).thenReturn(listaRestauranteEntity);

		
		//Act
		List<Restaurante> listaRestaurante = restauranteRepository.buscarRestaurantePorNome(nome);
		
		//Assert
		
		Assertions.assertNotNull(listaRestaurante);
		Assertions.assertEquals(restauranteEntity1.getNome(), listaRestaurante.get(0).getNome());
		assertThat(listaRestaurante).hasSize(2);
		Assertions.assertEquals(restauranteEntity2.getNome(), listaRestaurante.get(1).getNome());
	}
	
	@Test
	public void deveBuscarRestaurantePorCidade() {
		
		//Arrange
		
		String nome = "Praia Grande";
		
		RestauranteEntity restauranteEntity1 = Factory.createRestauranteEntity();
		RestauranteEntity restauranteEntity2 = Factory.createRestauranteEntity();

		List<RestauranteEntity> listaRestauranteEntity = new ArrayList<>();
		
		listaRestauranteEntity.add(restauranteEntity1);
		listaRestauranteEntity.add(restauranteEntity2);
		
		Mockito.when(repository.buscarPorCidade(nome)).thenReturn(listaRestauranteEntity);

		
		//Act
		List<Restaurante> listaRestaurante = restauranteRepository.buscarRestaurantePorCidade(nome);
		
		//Assert
		
		Assertions.assertNotNull(listaRestaurante);
		assertThat(listaRestaurante).hasSize(2);
		Assertions.assertEquals(restauranteEntity1.getCidade(), listaRestaurante.get(0).getCidade());
		Assertions.assertEquals(restauranteEntity2.getCidade(), listaRestaurante.get(1).getCidade());
	}
	
	
	@Test
	public void deveBuscarRestaurantePorCozinha() {
		
		//Arrange
		
		String nome = "Japonesa";
		
		RestauranteEntity restauranteEntity1 = Factory.createRestauranteEntity();
		RestauranteEntity restauranteEntity2 = Factory.createRestauranteEntity();

		List<RestauranteEntity> listaRestauranteEntity = new ArrayList<>();
		
		listaRestauranteEntity.add(restauranteEntity1);
		listaRestauranteEntity.add(restauranteEntity2);
		
		Mockito.when(repository.buscarPorCozinha(nome)).thenReturn(listaRestauranteEntity);

		
		//Act
		List<Restaurante> listaRestaurante = restauranteRepository.buscarRestaurantePorCozinha(nome);
		
		//Assert
		
		Assertions.assertNotNull(listaRestaurante);
		assertThat(listaRestaurante).hasSize(2);
		Assertions.assertEquals(restauranteEntity1.getGastronomia(), listaRestaurante.get(0).getGastronomia());
		Assertions.assertEquals(restauranteEntity2.getGastronomia(), listaRestaurante.get(1).getGastronomia());
	}

}

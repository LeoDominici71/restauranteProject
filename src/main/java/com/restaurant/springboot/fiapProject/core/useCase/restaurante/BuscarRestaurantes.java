package com.restaurant.springboot.fiapProject.core.useCase.restaurante;

import java.util.List;

import com.restaurant.springboot.fiapProject.core.RestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;

public class BuscarRestaurantes {

	private final RestauranteRepository repository;

	public BuscarRestaurantes(RestauranteRepository repository) {
		this.repository = repository;
	}

	public List<Restaurante> buscarRestaurantePorNome(String nome) {

		List<Restaurante> restaurantes = repository.buscarRestaurantePorNome(nome);
		return restaurantes;
	}

	public List<Restaurante> buscarRestaurantePorCozinha(String gastronomia) {

		List<Restaurante> restaurantes = repository.buscarRestaurantePorCozinha(gastronomia);
		return restaurantes;
	}
	
	public List<Restaurante> buscarRestaurantePorCidade(String cidade) {
		List<Restaurante> restaurantes = repository.buscarRestaurantePorCidade(cidade);
		return restaurantes;
		
	}

}

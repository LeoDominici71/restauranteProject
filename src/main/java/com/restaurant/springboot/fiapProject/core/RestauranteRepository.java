package com.restaurant.springboot.fiapProject.core;

import java.util.List;

import com.restaurant.springboot.fiapProject.core.entities.Restaurante;

public interface RestauranteRepository {
	
	Restaurante registroRestaurantes(Restaurante restaurante);
	
	
	List<Restaurante> buscarRestaurantePorCidade(String cidade);
	
	List<Restaurante> buscarRestaurantePorCozinha(String gastronomia);

	List<Restaurante> buscarRestaurantePorNome(String nome);

}

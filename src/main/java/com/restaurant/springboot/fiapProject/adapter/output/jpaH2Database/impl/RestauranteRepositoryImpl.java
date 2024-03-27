package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.core.RestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.utils.ApplicationUtils;

import lombok.NoArgsConstructor;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository{
	
	private final JpaRestauranteRepository repository;

    @Autowired
    public RestauranteRepositoryImpl(JpaRestauranteRepository repository) {
         this.repository = repository;
    }

	@Override
	public Restaurante registroRestaurantes(Restaurante restaurante) {
		// TODO Auto-generated method stub
		
		RestauranteEntity restauranteEntity = repository.save(new RestauranteEntity(restaurante));
		return new Restaurante(restauranteEntity);
	}

	@Override
	public List<Restaurante> buscarRestaurantePorNome(String nome) {
		// TODO Auto-generated method stub
		List<RestauranteEntity> lista = repository.buscarPorNome(nome);
		return ApplicationUtils.toRestaurante(lista);
	}

	@Override
	public List<Restaurante> buscarRestaurantePorCidade(String cidade) {
		// TODO Auto-generated method stub
		List<RestauranteEntity> lista = repository.buscarPorCidade(cidade);
		return ApplicationUtils.toRestaurante(lista);
	}

	@Override
	public List<Restaurante> buscarRestaurantePorCozinha(String gastronomia) {
		// TODO Auto-generated method stub
		List<RestauranteEntity> lista = repository.buscarPorCozinha(gastronomia);
		return ApplicationUtils.toRestaurante(lista);
	}

	
	
	
}

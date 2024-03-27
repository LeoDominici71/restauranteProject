package com.restaurant.springboot.fiapProject.core.useCase.restaurante;

import com.restaurant.springboot.fiapProject.core.RestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;

public class CadastroRestaurantes {

	private final RestauranteRepository repository;

	public CadastroRestaurantes(RestauranteRepository repository) {
		this.repository = repository;
	}

	public Restaurante registroRestaurantes(Restaurante restaurante) {
		// TODO Auto-generated method stub
		if(restaurante.getNome() == null || restaurante.getCidade() == null || restaurante.getEndereco() == null || restaurante.getGastronomia() == null || restaurante.getMesasDisponiveis() == null || restaurante.getHorarioAbertura() == null || restaurante.getHorarioFechamento() == null) {
		  throw new IllegalArgumentException("Não pode haver valores nulos no cadastro do restaurante");
		}
		Restaurante restauranteSalvo = repository.registroRestaurantes(restaurante);
		return restauranteSalvo;
	}

}

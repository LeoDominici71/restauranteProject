package com.restaurant.springboot.fiapProject.core.useCase.reserva;

import java.util.List;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;

public class ListaReservaPorRestaurante {
	
private final ReservaRepository repository;
	

	public ListaReservaPorRestaurante(ReservaRepository repository) {
		this.repository = repository;
	}
	
	public List<Reserva> listaReservaPorRestaurante(Long restauranteId){
		return repository.listaReservaPorRestaurante(restauranteId);
	}
	

}

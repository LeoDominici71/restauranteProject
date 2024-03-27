package com.restaurant.springboot.fiapProject.core.useCase.reserva;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;

public class ListaDeReservaPorDiaHora {
	
	
	private final ReservaRepository repository;
	

	public ListaDeReservaPorDiaHora(ReservaRepository repository) {
		this.repository = repository;
	}
	
	public List<Reserva> listaReservaPorDiaHora(Long restauranteId, LocalDate dia, LocalTime hora){
		return repository.listReservaPorDiaHora(restauranteId, dia, hora);
	}

}

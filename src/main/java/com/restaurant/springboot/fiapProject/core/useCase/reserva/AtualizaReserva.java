package com.restaurant.springboot.fiapProject.core.useCase.reserva;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;

public class AtualizaReserva {
	
	private final ReservaRepository repository;
	
	public AtualizaReserva(ReservaRepository repository) {
		this.repository = repository;
	}
	
	public Reserva atualizaReserva(Long reservaId, Reserva reserva) {
		
		return repository.atualizaReserva(reservaId, reserva);
	}


}

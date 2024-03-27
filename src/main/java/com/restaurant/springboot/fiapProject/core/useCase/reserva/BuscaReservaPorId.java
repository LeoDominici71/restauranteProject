package com.restaurant.springboot.fiapProject.core.useCase.reserva;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;

public class BuscaReservaPorId {
	
private final ReservaRepository repository;
	

	public BuscaReservaPorId(ReservaRepository repository) {
		this.repository = repository;
	}
	
	public Reserva buscarReservaPorId(Long reservaId) {
		
		return repository.buscarReserva(reservaId);
		
	}

}

package com.restaurant.springboot.fiapProject.core.useCase.reserva;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;

public class DeletarReserva {
	
	private final ReservaRepository repository;
	
	public DeletarReserva(ReservaRepository repository) {
		this.repository = repository;
	}
	
	public void deletarReserva(Long reservaId) {
		repository.deletarReserva(reservaId);
	}


}

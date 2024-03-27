package com.restaurant.springboot.fiapProject.core.useCase.reserva;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.utils.ApplicationUtils;

public class RegistroReserva {
	
	private final ReservaRepository repository;
	

	public RegistroReserva(ReservaRepository repository) {
		this.repository = repository;
	}
	
	public Reserva registroReserva(Reserva reserva) {
		
		if(reserva.getNomeUsuario() == null || reserva.getData() == null || reserva.getEmail() == null || reserva.getHora() == null || reserva.getQuantidadePessoas() == null) {
			throw new IllegalArgumentException("Não pode haver campos nulos no preenchimento da reserva");
		}
		
		if(reserva.getQuantidadePessoas() > ApplicationUtils.QUANTIDADE_MAXIMA) {
			throw new IllegalArgumentException("A capacidade maxima por reserva são 4 pessoas");
		}
		
		
		return repository.registroReserva(reserva);
	}

}

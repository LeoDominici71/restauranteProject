package com.restaurant.springboot.fiapProject.core;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.restaurant.springboot.fiapProject.core.entities.Reserva;

public interface ReservaRepository {
	
	Reserva registroReserva(Reserva reserva);
	
	Reserva buscarReserva(Long reservaId);
	
	void deletarReserva(Long reservaId);
	
	List<Reserva> listReservaPorDiaHora(Long restauranteId , LocalDate dia, LocalTime hora);
	
	Reserva atualizaReserva(Long reservaId, Reserva reserva);
	
	List<Reserva> listaReservaPorRestaurante(Long RestauranteId);
	
}

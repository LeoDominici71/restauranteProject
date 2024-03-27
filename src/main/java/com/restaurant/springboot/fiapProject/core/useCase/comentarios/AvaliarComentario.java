package com.restaurant.springboot.fiapProject.core.useCase.comentarios;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AvaliarComentario {
	
	public void avaliarComentario(LocalDate dataReserva,LocalTime horarioReserva, Boolean status) {
		
		if(status) {
			throw new IllegalArgumentException("Não pode registrar comentarios para reservas canceladas");
		}
		
		LocalDateTime dateTimeReserva = dataReserva.atTime(horarioReserva);
		
		if(LocalDateTime.now().isBefore(dateTimeReserva)) {
			throw new IllegalArgumentException("Você ainda nao visitou o restaurante");
		}
		
	} 

}

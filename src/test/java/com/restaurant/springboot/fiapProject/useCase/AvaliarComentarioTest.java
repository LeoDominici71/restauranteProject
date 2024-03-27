package com.restaurant.springboot.fiapProject.useCase;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.restaurant.springboot.fiapProject.core.useCase.comentarios.AvaliarComentario;

public class AvaliarComentarioTest {

	@Test
	public void avaliadorComentario() {
		// Arrange

		AvaliarComentario avaliador = new AvaliarComentario();
		LocalDate dataReserva = LocalDate.now();
		LocalTime horarioReserva = LocalTime.now();
		Boolean status = false;
		// Act & Assert

		Assertions.assertDoesNotThrow(() -> avaliador.avaliarComentario(dataReserva, horarioReserva, status));
	}

}

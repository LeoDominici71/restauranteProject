package com.restaurant.springboot.fiapProject.useCase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.restaurant.springboot.fiapProject.core.useCase.reserva.AvaliadorDeReservas;

public class AvaliadorDeReservasTest {

	@Test
	public void avaliadorDeReservas() {

		// Arrange
		AvaliadorDeReservas avaliador = new AvaliadorDeReservas();
		Integer mesas = 10;
		Integer totalReservas = 5;

		// Act & Assert
		Assertions.assertDoesNotThrow(() -> avaliador.avaliacaoQuantidadeMesas(mesas, totalReservas));
	}

}

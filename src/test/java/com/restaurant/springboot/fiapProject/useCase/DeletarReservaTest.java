package com.restaurant.springboot.fiapProject.useCase;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.DeletarReserva;

@ExtendWith(MockitoExtension.class)
public class DeletarReservaTest {

	@Mock
	private ReservaRepository repository;

	@InjectMocks
	private DeletarReserva deletarReserva;

	@Test
	public void deletarReservaPorId() {
		// Arrange
		Long Id = 1L;

		// Act
		Assertions.assertDoesNotThrow(() -> {
			deletarReserva.deletarReserva(Id);
		});
		// Assert
		Mockito.verify(repository, times(1)).deletarReserva(Id);

	}

}

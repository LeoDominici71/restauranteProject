package com.restaurant.springboot.fiapProject.useCase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.BuscaReservaPorId;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class BuscarReservaPorIdTest {

	@Mock
	private ReservaRepository repository;

	@InjectMocks
	private BuscaReservaPorId buscarReserva;

	@Test
	public void buscaReservaPorId() {
		
        //Arrange		
		Reserva reserva = Factory.createReserva();
		Mockito.when(repository.buscarReserva(1L)).thenReturn(reserva);
		
		//Act
		Reserva reservaSalvo = buscarReserva.buscarReservaPorId(1L);
		
        //Assert
		Assertions.assertNotNull(reservaSalvo);
		Assertions.assertEquals(reserva, reservaSalvo);
	}

}

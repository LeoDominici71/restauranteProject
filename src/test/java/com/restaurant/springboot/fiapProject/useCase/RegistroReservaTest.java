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
import com.restaurant.springboot.fiapProject.core.useCase.reserva.RegistroReserva;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class RegistroReservaTest {
	
	@Mock
	private ReservaRepository repository;
	
	@InjectMocks
	private RegistroReserva registroReserva;
	
	
	@Test
	public void registroReserva() {
		
		//Arrange
		Reserva reserva = Factory.createReserva();
		Mockito.when(repository.registroReserva(reserva)).thenReturn(reserva);
		
		//Act
		Reserva reservaSaved = registroReserva.registroReserva(reserva);
		
		//Assert
		Assertions.assertNotNull(reservaSaved);
		Assertions.assertEquals(reserva, reservaSaved);
	}

}

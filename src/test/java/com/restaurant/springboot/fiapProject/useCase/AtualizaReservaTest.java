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
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AtualizaReserva;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class AtualizaReservaTest {
	
	@Mock
	private ReservaRepository repository;
	
	@InjectMocks
	private AtualizaReserva atualizaReserva;
	
	
	@Test
	public void deveriaAtualizarUmaReserva() {
		
		//Arrange
		Reserva reserva = Factory.createReserva();
		Reserva reservaAtualizada = Factory.createReservaAtualizada();
		
		Mockito.when(repository.atualizaReserva(1L, reserva)).thenReturn(reservaAtualizada);
		
		//Act
		Reserva reservaAtualizadaTeste = atualizaReserva.atualizaReserva(1L, reserva);
		
		//Arrange
		Assertions.assertNotNull(reservaAtualizadaTeste);
		Assertions.assertNotEquals(reserva.getReservaCancelada(), reservaAtualizadaTeste.getReservaCancelada());


		
		
		
	}

}

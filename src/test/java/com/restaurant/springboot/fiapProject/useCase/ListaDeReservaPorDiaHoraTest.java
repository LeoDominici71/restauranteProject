package com.restaurant.springboot.fiapProject.useCase;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaDeReservaPorDiaHora;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class ListaDeReservaPorDiaHoraTest {
	
	@Mock
	private ReservaRepository repository;
	
	@InjectMocks
	private ListaDeReservaPorDiaHora listaDeReservaPorDiaHora;
	
	@Test
	public void listaDeReservaPorDiaHora() {
		//Arrange
		Reserva reserva = Factory.createReserva();
		Reserva reserva1 = Factory.createReserva();
		List<Reserva> reservas = new ArrayList<>();
		reservas.add(reserva);
		reservas.add(reserva1);
		Mockito.when(repository.listReservaPorDiaHora(1L, LocalDate.now(), LocalTime.of(18, 30))).thenReturn(reservas);
		
		//Act
		List<Reserva> reservaLista = listaDeReservaPorDiaHora.listaReservaPorDiaHora(1L, LocalDate.now(), LocalTime.of(18, 30));
		
		//Assert
		Assertions.assertNotNull(reservaLista);
		Assertions.assertEquals(reserva, reservaLista.get(0));
		Assertions.assertEquals(reserva1, reservaLista.get(1));
	} 

}

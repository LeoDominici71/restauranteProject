package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaDeReservaPorDiaHora;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class ListaDeReservaPorDiaHoraIT {
	
	@Autowired
	private JpaReservaRepository reservaRepository;

	@Autowired
	private JpaRestauranteRepository restauranteRepository;
	
	@Autowired
	private ListaDeReservaPorDiaHora listaDeReservaPorDiaHora;


	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void listaDeReservaPorDiaHora() {
		//Arrange
		reservaRepository.save(Factory.createReservaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());
		
		//Act
		List<Reserva> reservaLista = listaDeReservaPorDiaHora.listaReservaPorDiaHora(1L, LocalDate.of(2024, 3, 22), LocalTime.of(18, 30));
		
		//Assert
		Assertions.assertNotNull(reservaLista);
		Assertions.assertNotNull(reservaLista.get(0).getReservaId());

	} 
}

package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.RegistroReserva;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class RegistroReservaIT {
	
	@Autowired
	private RegistroReserva registroReserva;
	
	@Autowired
	private JpaRestauranteRepository repository;

	
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void registroReserva() {
		
		//Arrange
		repository.save(Factory.createRestauranteEntity());

		Reserva reserva = Factory.createReserva();
		
		
		//Act
		Reserva reservaSaved = registroReserva.registroReserva(reserva);
		
		//Assert
		Assertions.assertNotNull(reservaSaved);
		Assertions.assertNotNull(reservaSaved.getReservaId());
	}

}

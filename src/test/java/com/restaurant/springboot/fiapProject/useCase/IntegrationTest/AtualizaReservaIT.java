package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

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
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AtualizaReserva;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class AtualizaReservaIT {

	@Autowired
	private AtualizaReserva atualizaReserva;

	@Autowired
	private JpaReservaRepository reservaRepository;

	@Autowired
	private JpaRestauranteRepository restauranteRepository;

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveriaAtualizarUmaReserva() {

		// Arrange
		Reserva reserva = Factory.createReservaComTrue();
		reservaRepository.save(Factory.createReservaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());

		// Act
		Reserva reservaAtualizadaTeste = atualizaReserva.atualizaReserva(1L, reserva);

		// Arrange
		Assertions.assertNotNull(reservaAtualizadaTeste);
		Assertions.assertTrue(reservaAtualizadaTeste.getReservaCancelada());

	}

}

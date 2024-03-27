package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import static org.mockito.Mockito.times;

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
import com.restaurant.springboot.fiapProject.core.useCase.reserva.DeletarReserva;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class DeletarReservaPorIdIT {

	@Autowired
	private JpaReservaRepository reservaRepository;

	@Autowired
	private JpaRestauranteRepository restauranteRepository;

	@Autowired
	private DeletarReserva deletarReserva;

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deletarReservaPorId() {
		// Arrange
		reservaRepository.save(Factory.createReservaCanceladaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());

		//Act
		deletarReserva.deletarReserva(1L);
		// Assert
		Assertions.assertEquals(reservaRepository.count(), 0);

	}

}

package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaReservaPorRestaurante;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class ListaReservaPorRestauranteIT {

	@Autowired
	private ListaReservaPorRestaurante reservaRestaurante;

	@Autowired
	private JpaReservaRepository reservaRepository;

	@Autowired
	private JpaRestauranteRepository restauranteRepository;

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveListarReservaPorRestaurante() {
		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());

		// Act
		List<Reserva> reservas = reservaRestaurante.listaReservaPorRestaurante(1L);

		// Assert
		Assertions.assertNotNull(reservas);
		Assertions.assertNotNull(reservas.get(0).getReservaId());

	}
}

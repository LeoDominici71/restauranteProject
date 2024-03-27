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
import com.restaurant.springboot.fiapProject.core.useCase.reserva.BuscaReservaPorId;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class BuscaReservaPorIdIT {
	
	@Autowired
	private BuscaReservaPorId buscarReserva;
	
	@Autowired
	private JpaReservaRepository reservaRepository;
	
	@Autowired
	private JpaRestauranteRepository restauranteRepository;
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void buscaReservaPorId() {
		
        //Arrange		
		reservaRepository.save(Factory.createReservaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());
		//Act
		Reserva reservaSalvo = buscarReserva.buscarReservaPorId(1L);
		
        //Assert
		Assertions.assertNotNull(reservaSalvo);
		Assertions.assertNotNull(reservaSalvo.getReservaId());
	}

}

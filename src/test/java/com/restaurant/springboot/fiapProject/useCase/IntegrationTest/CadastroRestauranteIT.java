package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.CadastroRestaurantes;
import com.restaurant.springboot.fiapProject.factory.Factory;
@SpringBootTest
@Transactional
public class CadastroRestauranteIT {

	@Autowired
	private CadastroRestaurantes cadastroRestaurante;
	
	@Autowired
	private JpaRestauranteRepository repository;

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deveRegistrarRestaurantes() {

		// Arrange
		Restaurante restaurante = Factory.createRestaurante();
		repository.save(Factory.createRestauranteEntity());

		// Act
		Restaurante restauranteSaved = cadastroRestaurante.registroRestaurantes(restaurante);

		// Assert
		Assertions.assertNotNull(restauranteSaved);
		Assertions.assertNotNull(restauranteSaved.getId());


	}
}

package com.restaurant.springboot.fiapProject.useCase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.core.RestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.CadastroRestaurantes;
import com.restaurant.springboot.fiapProject.factory.Factory;
@ExtendWith(MockitoExtension.class)
public class CadastroRestauranteTest {
	
	@Mock
	private RestauranteRepository repository;
	
    @InjectMocks	
	private CadastroRestaurantes cadastroRestaurante;
  
	
	@Test
	public void deveRegistrarRestaurantes() {
		
		//Arrange
		Restaurante restaurante = Factory.createRestaurante();
		Mockito.when(repository.registroRestaurantes(Mockito.any(Restaurante.class))).thenReturn(restaurante);
		
		//Act
		Restaurante restauranteSaved = cadastroRestaurante.registroRestaurantes(restaurante);
		
		//Assert
		Assertions.assertNotNull(restauranteSaved);
		Assertions.assertEquals(restaurante, restauranteSaved);
		
		
	}

}

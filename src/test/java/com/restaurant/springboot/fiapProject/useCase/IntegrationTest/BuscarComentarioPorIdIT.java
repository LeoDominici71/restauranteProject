package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaComentarioRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.BuscarComentarioPorId;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class BuscarComentarioPorIdIT {
	
	
	@Autowired
	private BuscarComentarioPorId buscarComentarioPorId;
	
	@Autowired
	private JpaReservaRepository reservaRepository;
	
	@Autowired
	private JpaRestauranteRepository restauranteRepository;
	
	@Autowired
	private JpaComentarioRepository comentarioRepository;
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void buscarComentarioPorId() {

		// Arrange
		reservaRepository.save(Factory.createReservaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());
		comentarioRepository.save(Factory.createComentarioEntity());

		//Act
		Comentario comentarioSalvo = buscarComentarioPorId.buscarComentarioPorId(1L);
		
		//Assert
		Assertions.assertNotNull(comentarioSalvo);
		Assertions.assertNotNull(comentarioSalvo.getComentarioId());

	}


}

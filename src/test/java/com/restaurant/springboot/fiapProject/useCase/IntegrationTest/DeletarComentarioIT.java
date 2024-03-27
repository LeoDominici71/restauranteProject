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

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaComentarioRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.DeletarComentario;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class DeletarComentarioIT {

	@Autowired
	private JpaReservaRepository reservaRepository;

	@Autowired
	private JpaRestauranteRepository restauranteRepository;
	
	@Autowired
	private JpaComentarioRepository comentarioRepository;

	@Autowired
	private DeletarComentario deletarComentario;

	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void deletarComentario() {
		// Arrange
		reservaRepository.save(Factory.createReservaCanceladaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());
		comentarioRepository.save(Factory.createComentarioEntity());

		// Act
		deletarComentario.deletarComentario(1L);
		// Assert
		Assertions.assertEquals(comentarioRepository.count(), 0);

	}

}

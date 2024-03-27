package com.restaurant.springboot.fiapProject.useCase.IntegrationTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.RegistroComentario;
import com.restaurant.springboot.fiapProject.factory.Factory;

@SpringBootTest
@Transactional
public class RegistroComentarioIT {
	
	@Autowired
	private RegistroComentario registroComentario;
	
	@Autowired
	private JpaReservaRepository reservaRepository;
	
	@Autowired
	private JpaRestauranteRepository restauranteRepository;
	
	
	@Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
	public void registroComentario() {
		//Arrange
		Comentario comentario = Factory.createComentario();
		reservaRepository.save(Factory.createReservaEntity());
		restauranteRepository.save(Factory.createRestauranteEntity());
		
		//Act
		Comentario comentarioSalvo = registroComentario.registroComentario(comentario);
		
		//Assert
		Assertions.assertNotNull(comentarioSalvo);
		Assertions.assertNotNull(comentarioSalvo.getComentarioId());

		
		
	} 

}

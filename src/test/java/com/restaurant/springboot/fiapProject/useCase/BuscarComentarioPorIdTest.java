package com.restaurant.springboot.fiapProject.useCase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.core.ComentarioRepository;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.BuscarComentarioPorId;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class BuscarComentarioPorIdTest {

	@Mock
	private ComentarioRepository repository;

	@InjectMocks
	private BuscarComentarioPorId buscarComentarioPorId;

	@Test
	public void buscarComentarioPorId() {

		// Arrange
		Comentario comentario = Factory.createComentario();
		Mockito.when(repository.buscarComentarioPorId(1L)).thenReturn(comentario);
		
		//Act
		Comentario comentarioSalvo = buscarComentarioPorId.buscarComentarioPorId(1L);
		
		//Assert
		Assertions.assertNotNull(comentarioSalvo);
		Assertions.assertEquals(comentario, comentarioSalvo);

	}

}

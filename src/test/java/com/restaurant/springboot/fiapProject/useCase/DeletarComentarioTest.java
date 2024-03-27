package com.restaurant.springboot.fiapProject.useCase;

import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.core.ComentarioRepository;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.DeletarComentario;

@ExtendWith(MockitoExtension.class)
public class DeletarComentarioTest {

	@Mock
	private ComentarioRepository repository;

	@InjectMocks
	private DeletarComentario deletarComentario;

	@Test
	public void deletarComentario() {
		// Arrange
		Long Id = 1L;

		// Act
		Assertions.assertDoesNotThrow(() -> {
			deletarComentario.deletarComentario(Id);
		});
		// Assert
		Mockito.verify(repository, times(1)).deletarComentario(Id);
	}

}

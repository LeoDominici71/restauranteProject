package com.restaurant.springboot.fiapProject.jpaH2Database.impl;

import static org.mockito.Mockito.times;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaComentarioRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ComentarioEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.impl.ComentarioRepositoryImpl;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.AvaliarComentario;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class ComentarioRepositoryImplTest {

	@Mock
	private JpaComentarioRepository jpaComentarioRepository;

	@Mock
	private JpaReservaRepository jpaReservaRepository;

	@Mock
	private JpaRestauranteRepository jpaRestauranteRepository;

	@InjectMocks
	private ComentarioRepositoryImpl comentarioRepository;

	@Mock
	private AvaliarComentario avaliador;

	@Test
	public void registroComentario() {

		// Arrange
		Comentario comentario = Factory.createComentario();
		ComentarioEntity comentarioEntity = Factory.createComentarioEntity();
		Optional<ReservaEntity> reservaEntity = Factory.createReservaEntityOptionalRestauranteWithId();
		Mockito.when(jpaReservaRepository.findById(1L)).thenReturn(reservaEntity);
		Mockito.doNothing().when(avaliador).avaliarComentario(reservaEntity.get().getData(),
				reservaEntity.get().getHora(), reservaEntity.get().getReservaCancelada());
		Mockito.when(jpaComentarioRepository.save(comentarioEntity)).thenReturn(comentarioEntity);

		// Act
		Comentario comentarioSaved = comentarioRepository.registroComentario(comentario);

		// Assert
		Assertions.assertNotNull(comentarioSaved);
		Mockito.verify(jpaComentarioRepository, times(1)).save(comentarioEntity);

	}

	@Test
	public void deveBuscarComentarioPorId() {
		// Arrange
		Optional<ComentarioEntity> comentarioEntity = Factory.createComentarioEntityOptional();
		Mockito.when(jpaComentarioRepository.findById(1L)).thenReturn(comentarioEntity);

		// Act
		Comentario comentario = comentarioRepository.buscarComentarioPorId(1L);

		// Assert
		Assertions.assertNotNull(comentario);
		Mockito.verify(jpaComentarioRepository, times(1)).findById(1L);

	}

	@Test
	public void deveDeletarComentarioPorId() {
		// Arrange
		Optional<ComentarioEntity> comentarioEntity = Factory.createComentarioEntityOptional();
		Mockito.when(jpaComentarioRepository.findById(1L)).thenReturn(comentarioEntity);

		// Act
		Assertions.assertDoesNotThrow(() -> {
			comentarioRepository.deletarComentario(1L);
		});

		// Assert
		Mockito.verify(jpaComentarioRepository, times(1)).deleteById(1L);
	}

}

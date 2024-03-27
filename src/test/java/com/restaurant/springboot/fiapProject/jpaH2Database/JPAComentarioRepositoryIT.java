package com.restaurant.springboot.fiapProject.jpaH2Database;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaComentarioRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ComentarioEntity;
import com.restaurant.springboot.fiapProject.factory.Factory;

@DataJpaTest
public class JPAComentarioRepositoryIT {
	
	@Autowired
	private JpaComentarioRepository repository;
	
	@Test
	public void deveriaRegistrarUmComentario() {
		// Arrange
		ComentarioEntity comentario = Factory.createComentarioEntity();

		// Act
		ComentarioEntity comentarioSalvo = repository.save(comentario);

		// Assert
		Assertions.assertNotNull(comentarioSalvo);
		assertThat(comentarioSalvo).hasNoNullFieldsOrProperties();
		Assertions.assertEquals(comentarioSalvo, comentario);

	}
	
	@Test
	public void deveriaBuscarUmComentarioPorId() {
		// Arrange
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);

		// Act
		ComentarioEntity comentarioSalvo = repository.getReferenceById(comentario.getComentarioId());

		// Assert
		Assertions.assertNotNull(comentarioSalvo);
		assertThat(comentarioSalvo).hasNoNullFieldsOrProperties();
		Assertions.assertEquals(comentarioSalvo, comentario);

	}
	
	@Test
	public void deveriaDeletarComentarioPorId() {
		// Arrange
		ComentarioEntity comentario = Factory.createComentarioEntity();
		repository.save(comentario);

		// Act
		repository.deleteById(comentario.getComentarioId());
		Optional<ComentarioEntity> comentarioSalvo = repository.findById(comentario.getComentarioId());

		// Assert
		Assertions.assertFalse(comentarioSalvo.isPresent());


	}

}

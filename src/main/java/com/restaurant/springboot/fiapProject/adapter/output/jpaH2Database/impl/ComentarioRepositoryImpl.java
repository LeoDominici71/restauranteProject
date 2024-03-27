package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaComentarioRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ComentarioEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.core.ComentarioRepository;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.AvaliarComentario;
import com.restaurant.springboot.fiapProject.utils.ApplicationUtils;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ComentarioRepositoryImpl implements ComentarioRepository {

	private final JpaComentarioRepository repository;

	private final JpaReservaRepository reservaRepository;

	private final AvaliarComentario avaliador;

	@Autowired
	ComentarioRepositoryImpl(JpaComentarioRepository repository, JpaReservaRepository reservaRepository,
			AvaliarComentario avaliador) {
		this.repository = repository;
		this.reservaRepository = reservaRepository;
		this.avaliador = avaliador;
	}

	@Override
	public Comentario registroComentario(Comentario comentario) {
		// TODO Auto-generated method stub
		ComentarioEntity comentarioEntity = new ComentarioEntity(comentario);

		Optional<ReservaEntity> reservaEntity = reservaRepository.findById(comentario.getReservaId());
		ReservaEntity reservaEnt = reservaEntity
				.orElseThrow(() -> new EntityNotFoundException("Reserva nao encontrada"));

		// avaliando data do comentario
		avaliador.avaliarComentario(reservaEnt.getData(), reservaEnt.getHora(), reservaEnt.getReservaCancelada());

		comentarioEntity.setReserva(reservaEnt);
		comentarioEntity = repository.save(comentarioEntity);
		return ApplicationUtils.toComentario(comentarioEntity);
	}

	@Override
	public Comentario buscarComentarioPorId(Long comentarioId) {
		// TODO Auto-generated method stub
		Optional<ComentarioEntity> comentarioEntity = repository.findById(comentarioId);
		ComentarioEntity comentarioEnt = comentarioEntity
				.orElseThrow(() -> new EntityNotFoundException("Comentario nao encontrado"));
		return ApplicationUtils.toComentario(comentarioEnt);
	}

	@Override
	public void deletarComentario(Long comentarioId) {
		// TODO Auto-generated method stub
		try {
			ComentarioEntity comentarioEntity = repository.findById(comentarioId)
					.orElseThrow(() -> new EntityNotFoundException("Reserva nao encontrada"));

			comentarioEntity.setReserva(null);
			repository.deleteById(comentarioId);

		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found " + comentarioId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não é possivel deletar o comentario");
		}

	}

}

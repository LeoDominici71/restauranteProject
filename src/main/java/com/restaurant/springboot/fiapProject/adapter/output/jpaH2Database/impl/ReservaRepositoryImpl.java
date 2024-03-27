package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AvaliadorDeReservas;
import com.restaurant.springboot.fiapProject.utils.ApplicationUtils;

import jakarta.persistence.EntityNotFoundException;

@Component
public class ReservaRepositoryImpl implements ReservaRepository {

	private final JpaReservaRepository reservaRepository;
	private final JpaRestauranteRepository restauranteRepository;
	private final AvaliadorDeReservas gerenciadorDeReservas;

	@Autowired
	ReservaRepositoryImpl(JpaReservaRepository reservaRepository, JpaRestauranteRepository restauranteRepository,
			AvaliadorDeReservas gerenciadorDeReservas) {
		this.reservaRepository = reservaRepository;
		this.restauranteRepository = restauranteRepository;
		this.gerenciadorDeReservas = gerenciadorDeReservas;
	}

	@Override
	public Reserva registroReserva(Reserva reserva) {
		// TODO Auto-generated method stub
		ReservaEntity reservaEntity = new ReservaEntity(reserva);
		// busca do restaurante por id e atualização das mesas.
		if (reserva.getRestauranteId() != null) {
			// busca do restaurante
			Optional<RestauranteEntity> restauranteEntityOptional = restauranteRepository
					.findById(reserva.getRestauranteId());
			RestauranteEntity restauranteEntity = restauranteEntityOptional
					.orElseThrow(() -> new EntityNotFoundException("Restaurante nao encontrado"));

			reservaEntity.setRestaurante(restauranteEntity);
		} else
		// busca do restaurante por nome e atualização das mesas.
		if (reserva.getRestauranteNome() != null) {
			// busca do restaurante
			List<RestauranteEntity> restauranteEntityLista = restauranteRepository
					.buscarPorNome(reserva.getRestauranteNome());

			RestauranteEntity restauranteEntity = restauranteEntityLista.stream().findFirst()
					.orElseThrow(() -> new EntityNotFoundException("Restaurante não encontrado"));

			reservaEntity.setRestaurante(restauranteEntity);
		}

		// buscando lista de reservas
		List<ReservaEntity> listReservaEntity = reservaRepository.encontrarReservasPorDataDia(
				reservaEntity.getRestaurante().getId(), reserva.getData(), reserva.getHora());
		Integer numerReservas = listReservaEntity.size();

		// validando o numero de reservas
		gerenciadorDeReservas.avaliacaoQuantidadeMesas(reservaEntity.getRestaurante().getMesasDisponiveis(),
				numerReservas);

		reservaEntity = reservaRepository.save(reservaEntity);
		return ApplicationUtils.toReserva(reservaEntity);
	}

	@Override
	public Reserva buscarReserva(Long reservaId) {
		// TODO Auto-generated method stub
		Optional<ReservaEntity> reservaEntity = reservaRepository.findById(reservaId);
		ReservaEntity reserva = reservaEntity.orElseThrow(() -> new EntityNotFoundException("Reserva nao encontrada"));
		return ApplicationUtils.toReserva(reserva);
	}

	@Override
	public void deletarReserva(Long reservaId) {
		// TODO Auto-generated method stub
		try {
			ReservaEntity reservaEntity = reservaRepository.findById(reservaId)
					.orElseThrow(() -> new EntityNotFoundException("Reserva nao encontrada"));
			if (!reservaEntity.getReservaCancelada()) {
				throw new IllegalArgumentException(
						"Primeiro deve mover o status da reserva para cancelada antes de realizar o cancelamento");
			}
            reservaEntity.setRestaurante(null);
			reservaRepository.deleteById(reservaId);
			
			
		} catch (EmptyResultDataAccessException e) {
			throw new EntityNotFoundException("Id not found " + reservaId);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException(
					"Não é possivel deletar a reserva caso a visita ao restaurante já esteja sido feita e um comentario realizado");
		}
	}

	@Override
	public List<Reserva> listReservaPorDiaHora(Long restauranteId, LocalDate dia, LocalTime hora) {
		// TODO Auto-generated method stub
		List<ReservaEntity> reservaEntity = reservaRepository.encontrarReservasPorDataDia(restauranteId, dia, hora);
		List<Reserva> reserva = reservaEntity.stream().map(t -> ApplicationUtils.toReserva(t))
				.collect(Collectors.toList());
		return reserva;
	}

	@Override
	public Reserva atualizaReserva(Long reservaId, Reserva reserva) {
		// TODO Auto-generated method stub

		Optional<ReservaEntity> reservaEntityOptional = reservaRepository.findById(reservaId);
		ReservaEntity reservaEntity = reservaEntityOptional
				.orElseThrow(() -> new EntityNotFoundException("Reserva nao encontrada"));
		ReservaEntity reservaUpdated = ApplicationUtils.atualizaReservaEntity(reservaEntity, reserva);
		reservaRepository.save(reservaUpdated);

		return ApplicationUtils.toReserva(reservaEntity);
	}

	@Override
	public List<Reserva> listaReservaPorRestaurante(Long RestauranteId) {
		// TODO Auto-generated method stub
		List<ReservaEntity> listaReserva = reservaRepository.encontrarReservasPorRestaurante(RestauranteId);
		return listaReserva.stream().map(l -> ApplicationUtils.toReserva(l)).collect(Collectors.toList());
	}

}

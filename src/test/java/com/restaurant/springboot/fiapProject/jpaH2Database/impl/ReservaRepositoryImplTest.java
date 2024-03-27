package com.restaurant.springboot.fiapProject.jpaH2Database.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.impl.ReservaRepositoryImpl;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AvaliadorDeReservas;
import com.restaurant.springboot.fiapProject.factory.Factory;

@ExtendWith(MockitoExtension.class)
public class ReservaRepositoryImplTest {

	@Mock
	private JpaReservaRepository jpaReservaRepository;

	@Mock
	private JpaRestauranteRepository jpaRestauranteRepository;

	@InjectMocks
	private ReservaRepositoryImpl reservaRepository;

	@Mock
	private AvaliadorDeReservas avaliador;

	@Test
	public void deveRegistrarReserva() {

		// Arrange
		Reserva reserva = Factory.createReservaComId();
		ReservaEntity reservaEntity = Factory.createReservaEntity();
		ReservaEntity reservaEntityRetorno = Factory.createReservaEntity();
		reservaEntityRetorno.setReservaId(1L);
		Optional<RestauranteEntity> restauranteEntityOptional = Factory.createRestauranteEntityOptional();
		Optional<RestauranteEntity> restauranteEntityOptionalRetorno = Factory.createRestauranteEntityOptional();
		restauranteEntityOptionalRetorno.get().setId(1L);
		RestauranteEntity restauranteEntityRetorno = Factory.createRestauranteEntity();
		restauranteEntityRetorno.setId(1L);
		List<RestauranteEntity> listaRestauranteEntity = new ArrayList<>();
		listaRestauranteEntity.add(restauranteEntityRetorno);
		List<ReservaEntity> reservasLista = new ArrayList<>();
		reservasLista.add(reservaEntityRetorno);
		Mockito.when(jpaRestauranteRepository.findById(reserva.getRestauranteId()))
				.thenReturn(restauranteEntityOptional);
		Mockito.when(jpaReservaRepository.save(Mockito.any(ReservaEntity.class))).thenReturn(reservaEntityRetorno);
		Mockito.doNothing().when(avaliador).avaliacaoQuantidadeMesas(Mockito.anyInt(), Mockito.anyInt());

		// Act
		Reserva reservaSalvo = reservaRepository.registroReserva(reserva);

		// Assert
		Assertions.assertNotNull(reservaSalvo);
		Assertions.assertEquals(reservaSalvo.getQuantidadePessoas(), reservaEntityRetorno.getQuantidadePessoas());
		Mockito.verify(jpaReservaRepository, times(1)).save(reservaEntity);

		
	}

	@Test
	public void deveRegistrarReservaBuscaPorNome() {

		// Arrange
		Reserva reserva = Factory.createReserva();
		ReservaEntity reservaEntity = Factory.createReservaEntity();
		RestauranteEntity restauranteEntity = Factory.createRestauranteEntity();
		RestauranteEntity restauranteEntityRetorno = Factory.createRestauranteEntity();
		restauranteEntityRetorno.setId(1L);
		List<RestauranteEntity> listaRestauranteEntity = new ArrayList<>();
		listaRestauranteEntity.add(restauranteEntityRetorno);

		Mockito.when(jpaRestauranteRepository.buscarPorNome(restauranteEntity.getNome()))
				.thenReturn(listaRestauranteEntity);
		Mockito.when(jpaReservaRepository.save(Mockito.any(ReservaEntity.class))).thenReturn(reservaEntity);
		Mockito.doNothing().when(avaliador).avaliacaoQuantidadeMesas(Mockito.anyInt(), Mockito.anyInt());

		// Act
		Reserva reservaSalvo = reservaRepository.registroReserva(reserva);

		// Assert
		Assertions.assertNotNull(reservaSalvo);
		Mockito.verify(jpaReservaRepository, times(1)).save(reservaEntity);


	}

	@Test
	public void deveriaBuscarReserva() {

		// Arrange
		Optional<ReservaEntity> reservaEntity = Factory.createReservaEntityOptional();
		Mockito.when(jpaReservaRepository.findById(1L)).thenReturn(reservaEntity);

		// Act
		Reserva reservaSalvo = reservaRepository.buscarReserva(1L);

		// Assert
		Assertions.assertNotNull(reservaSalvo);
		Assertions.assertEquals(reservaEntity.get().getQuantidadePessoas(), reservaSalvo.getQuantidadePessoas());
		Assertions.assertEquals(reservaEntity.get().getRestaurante().getId(),reservaSalvo.getRestauranteId());
		Mockito.verify(jpaReservaRepository, times(1)).findById(1L);


	}

	@Test
	public void deveriaDeletarReserva() {
		Optional<ReservaEntity> reservaEntity = Factory.createReservaEntityOptional();

		Mockito.when(jpaReservaRepository.findById(1L)).thenReturn(reservaEntity);

		// Act
		Assertions.assertDoesNotThrow(() -> {
			reservaRepository.deletarReserva(1L);
		});
		// Assert
		Mockito.verify(jpaReservaRepository, times(1)).deleteById(1L);

	}

	@Test
	public void deveListarReservaPorDiaeHora() {
		// Arrange
		ReservaEntity reserva = Factory.createReservaEntity();
		ReservaEntity reserva1 = Factory.createReservaEntity();
		List<ReservaEntity> reservas = new ArrayList<>();
		reservas.add(reserva);
		reservas.add(reserva1);
		Mockito.when(jpaReservaRepository.encontrarReservasPorDataDia(1L, LocalDate.now(), LocalTime.of(18, 30)))
				.thenReturn(reservas);

		// Act
		List<Reserva> reservasaved = reservaRepository.listReservaPorDiaHora(1L, LocalDate.now(), LocalTime.of(18, 30));

		// Assert
		Assertions.assertNotNull(reservasaved);
		assertThat(reservasaved).hasSize(2);
		Mockito.verify(jpaReservaRepository, times(1)).encontrarReservasPorDataDia(1L, LocalDate.now(), LocalTime.of(18, 30));



	}

	@Test
	public void deveAtualizarReserva() {
		// Arrange
		Optional<ReservaEntity> reservaEntityOptional = Factory.createReservaEntityOptional();
		ReservaEntity reservaEntity = Factory.createReservaEntity();
		Reserva reservaRequest = Factory.createReserva();
		Mockito.when(jpaReservaRepository.save(Mockito.any(ReservaEntity.class))).thenReturn(reservaEntity);
		Mockito.when(jpaReservaRepository.findById(1L)).thenReturn(reservaEntityOptional);

		// Act
		Reserva reserva = reservaRepository.atualizaReserva(1L, reservaRequest);

		// Assert
		Assertions.assertNotNull(reserva);
		Assertions.assertEquals(reservaEntity.getQuantidadePessoas(), reserva.getQuantidadePessoas());
		Mockito.verify(jpaReservaRepository, times(1)).save(reservaEntity);

	}

	@Test
	public void develistarReservaPorRestaurante() {
		// Arrange
		ReservaEntity reserva = Factory.createReservaEntity();
		ReservaEntity reserva1 = Factory.createReservaEntity();
		List<ReservaEntity> reservas = new ArrayList<>();
		reservas.add(reserva);
		reservas.add(reserva1);
		Mockito.when(jpaReservaRepository.encontrarReservasPorRestaurante(1L))
				.thenReturn(reservas);
		
		//Act
		List<Reserva> reservaLista = reservaRepository.listaReservaPorRestaurante(1L);
		
		//Assert
		Assertions.assertNotNull(reservaLista);
		assertThat(reservaLista).hasSize(2);
		Mockito.verify(jpaReservaRepository, times(1)).encontrarReservasPorRestaurante(1L);


	}

}

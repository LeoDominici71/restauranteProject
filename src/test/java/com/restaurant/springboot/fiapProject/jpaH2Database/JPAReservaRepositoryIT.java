package com.restaurant.springboot.fiapProject.jpaH2Database;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaReservaRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.factory.Factory;

@DataJpaTest
public class JPAReservaRepositoryIT {

	@Autowired
	private JpaReservaRepository repository;

	@Autowired
	private JpaRestauranteRepository resturanteRepository;

	@Test
	public void deveriaEncontrarReservasQueNaoEstaoCanceladas() {

		// Arrange
		ReservaEntity reserva1 = Factory.createReservaEntity();
		ReservaEntity reserva2 = Factory.createReservaEntity();

		repository.save(reserva1);
		repository.save(reserva2);

		// Act
		List<ReservaEntity> listaReserva = repository.encontrarReservasNaoCanceladas();

		// Assert
		Assertions.assertNotNull(listaReserva.get(0));
		Assertions.assertNotNull(listaReserva.get(1));
		assertThat(listaReserva).hasSize(2);

	}

	@Test
	public void deveriaEncontrarReservasQueEstaoCanceladas() {

		// Arrange
		ReservaEntity reserva1 = Factory.createReservaCanceladaEntity();
		ReservaEntity reserva2 = Factory.createReservaCanceladaEntity();

		repository.save(reserva1);
		repository.save(reserva2);

		// Act
		List<ReservaEntity> listaReserva = repository.encontrarReservasNaoCanceladas();

		// Assert
		Assertions.assertTrue(listaReserva.isEmpty());
		assertThat(listaReserva).hasSize(0);

	}


	@Test
	public void naoDeveriaEncontrarReservasPorDataDia() {

		// Arrange
		Long id = 2L;
		LocalDate data = LocalDate.now();
		LocalTime tempo = LocalTime.of(18, 30);

		// Criando restaurante com id 1
		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		resturanteRepository.save(restaurante1);

		// registrando o restaurante 1 nas duas reservas
		ReservaEntity reserva1 = Factory.createReservaEntity();
		reserva1.setRestaurante(restaurante1);
		ReservaEntity reserva2 = Factory.createReservaEntity();
		reserva2.setRestaurante(restaurante1);

		repository.save(reserva1);
		repository.save(reserva2);

		// Act
		List<ReservaEntity> listaReserva = repository.encontrarReservasPorDataDia(id, data, tempo);

		// Assert
		Assertions.assertTrue(listaReserva.isEmpty());
		assertThat(listaReserva).hasSize(0);

	}

	@Test
	public void deveriaEncontrarReservasPorIdDeRestaurante() {

		// Arrange

		// Criando restaurante
		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		resturanteRepository.save(restaurante1);

		// registrando o restaurante 1 nas duas reservas
		ReservaEntity reserva1 = Factory.createReservaEntity();
		reserva1.setRestaurante(restaurante1);
		ReservaEntity reserva2 = Factory.createReservaEntity();
		reserva2.setRestaurante(restaurante1);

		repository.save(reserva1);
		repository.save(reserva2);

		// Act
		List<ReservaEntity> listaReserva = repository.encontrarReservasPorRestaurante(restaurante1.getId());

		// Assert
		Assertions.assertNotNull(listaReserva.get(0));
		Assertions.assertNotNull(listaReserva.get(1));
		assertThat(listaReserva).hasSize(2);

	}

	@Test
	public void naoDeveriaEncontrarReservasPorIddeRestauranteSeOIdNaoExistir() {

		// Arrange

		Long idInexistente = 24L;

		// Criando restaurante
		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		resturanteRepository.save(restaurante1);

		// registrando o restaurante 1 nas duas reservas
		ReservaEntity reserva1 = Factory.createReservaEntity();
		reserva1.setRestaurante(restaurante1);
		ReservaEntity reserva2 = Factory.createReservaEntity();
		reserva2.setRestaurante(restaurante1);

		repository.save(reserva1);
		repository.save(reserva2);

		// Act
		List<ReservaEntity> listaReserva = repository.encontrarReservasPorRestaurante(idInexistente);

		// Assert
		Assertions.assertTrue(listaReserva.isEmpty());
		assertThat(listaReserva).hasSize(0);

	}

	@Test
	public void deveriaRegistrarUmaReserva() {
		// Arrange
		ReservaEntity reserva = Factory.createReservaEntity();

		// Act
		ReservaEntity reservaSalvo = repository.save(reserva);

		// Assert
		Assertions.assertNotNull(reservaSalvo);
		assertThat(reservaSalvo).hasNoNullFieldsOrProperties();
		Assertions.assertEquals(reservaSalvo, reserva);

	}

	@Test
	public void deveriaBuscarUmaReservaPorId() {
		// Arrange
		ReservaEntity reserva = Factory.createReservaEntity();
		repository.save(reserva);

		// Act
		ReservaEntity reservaSalvo = repository.getReferenceById(reserva.getReservaId());

		// Assert
		Assertions.assertNotNull(reservaSalvo);
		assertThat(reservaSalvo).hasNoNullFieldsOrProperties();
		Assertions.assertEquals(reservaSalvo, reserva);

	}
	
	@Test
	public void deveriaDeletarUmaReservaPorId() {
		// Arrange
		ReservaEntity reserva = Factory.createReservaEntity();
		repository.save(reserva);

		// Act
		repository.deleteById(reserva.getReservaId());
		Optional<ReservaEntity> reservaSalvo = repository.findById(reserva.getReservaId());

		// Assert
		Assertions.assertFalse(reservaSalvo.isPresent());


	}


}

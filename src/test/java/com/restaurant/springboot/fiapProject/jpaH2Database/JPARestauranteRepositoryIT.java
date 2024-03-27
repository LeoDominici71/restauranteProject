package com.restaurant.springboot.fiapProject.jpaH2Database;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.JpaRestauranteRepository;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.factory.Factory;

@DataJpaTest
public class JPARestauranteRepositoryIT {

	@Autowired
	private JpaRestauranteRepository repository;

	@Test
	public void deveriaBuscarRestaurantesPorCidade() {

		// Arrange

		String cidadeNome = "Praia Grande";

		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		RestauranteEntity restaurante2 = Factory.createRestauranteEntity();

		repository.save(restaurante1);
		repository.save(restaurante2);

		// Act
		List<RestauranteEntity> listaRest = repository.buscarPorCidade(cidadeNome);

		// Assert
		Assertions.assertNotNull(listaRest.get(0));
		Assertions.assertNotNull(listaRest.get(1));
		assertThat(listaRest).hasSize(2);

	}

	@Test
	public void deveriaRetornarListaVaziaQuandoBuscaRestaurantePorCidadeQueNaoExiste() {

		// Arrange

		String cidadeNome = "Long Beach";

		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		RestauranteEntity restaurante2 = Factory.createRestauranteEntity();

		repository.save(restaurante1);
		repository.save(restaurante2);

		// Act
		List<RestauranteEntity> listaRest = repository.buscarPorCidade(cidadeNome);

		// Assert
		Assertions.assertTrue(listaRest.isEmpty());
		assertThat(listaRest).hasSize(0);

	}

	@Test
	public void deveriaBuscarResturantesPorNome() {

		// Arrange

		String restauranteNome = "Nayumi";

		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		RestauranteEntity restaurante2 = Factory.createRestauranteEntity();

		repository.save(restaurante1);
		repository.save(restaurante2);

		// Act
		List<RestauranteEntity> listaRest = repository.buscarPorNome(restauranteNome);

		// Assert
		Assertions.assertNotNull(listaRest.get(0));
		Assertions.assertNotNull(listaRest.get(1));
		assertThat(listaRest).hasSize(2);

	}

	@Test
	public void deveriaRetornarListaVaziaQuandoBuscaRestaurantePorNomeQueNaoExiste() {

		// Arrange

		String restauranteNome = "Nayami";

		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		RestauranteEntity restaurante2 = Factory.createRestauranteEntity();

		repository.save(restaurante1);
		repository.save(restaurante2);

		// Act
		List<RestauranteEntity> listaRest = repository.buscarPorCidade(restauranteNome);

		// Assert
		Assertions.assertTrue(listaRest.isEmpty());
		assertThat(listaRest).hasSize(0);

	}

	@Test
	public void deveriaBuscarRestaurantesPorCozinha() {

		// Arrange

		String cozinha = "Japonesa";

		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		RestauranteEntity restaurante2 = Factory.createRestauranteEntity();

		repository.save(restaurante1);
		repository.save(restaurante2);

		// Act
		List<RestauranteEntity> listaRest = repository.buscarPorCozinha(cozinha);

		// Assert
		Assertions.assertNotNull(listaRest.get(0));
		Assertions.assertNotNull(listaRest.get(1));
		assertThat(listaRest).hasSize(2);

	}
	
	@Test
	public void deveriaRetornarListaVaziaQuandoBuscaRestaurantePorCozinhaQueNaoExiste() {

		// Arrange

		String cozinha = "Mineira";

		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();
		RestauranteEntity restaurante2 = Factory.createRestauranteEntity();

		repository.save(restaurante1);
		repository.save(restaurante2);

		// Act
		List<RestauranteEntity> listaRest = repository.buscarPorCidade(cozinha);

		// Assert
		Assertions.assertTrue(listaRest.isEmpty());
		assertThat(listaRest).hasSize(0);

	}

	@Test
	public void deveriaSalvarRestaurantes() {

		// Arrange
		RestauranteEntity restaurante1 = Factory.createRestauranteEntity();

		// Act
		RestauranteEntity restauranteSalvo = repository.save(restaurante1);

		// Assert
		Assertions.assertNotNull(restauranteSalvo);
		assertThat(restauranteSalvo).hasNoNullFieldsOrProperties();
		Assertions.assertEquals(restaurante1, restauranteSalvo);

	}

}

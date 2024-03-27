package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;

@Repository
public interface JpaRestauranteRepository extends JpaRepository<RestauranteEntity, Long>{

	
	@Query(value = "SELECT * FROM tb_restaurant r WHERE r.nome = ?1", nativeQuery = true)
    List<RestauranteEntity> buscarPorNome(String nome);
	
	@Query(value = "SELECT * FROM tb_restaurant r WHERE r.cidade = ?1", nativeQuery = true)
    List<RestauranteEntity> buscarPorCidade(String cidade);
	
	@Query(value = "SELECT * FROM tb_restaurant r WHERE r.gastronomia = ?1", nativeQuery = true)
    List<RestauranteEntity> buscarPorCozinha(String gastronomia);
	
}

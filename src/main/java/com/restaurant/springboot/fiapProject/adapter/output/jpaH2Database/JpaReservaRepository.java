package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;

public interface JpaReservaRepository extends JpaRepository<ReservaEntity, Long> {

	@Query(value = "SELECT * FROM tb_reserva WHERE reserva_cancelada = false", nativeQuery = true)
	List<ReservaEntity> encontrarReservasNaoCanceladas();

	@Query(value = "SELECT * FROM tb_reserva WHERE restaurante_id = ?1 AND data = ?2 AND hora = ?3", nativeQuery = true)
	List<ReservaEntity> encontrarReservasPorDataDia(Long restauranteId, LocalDate dia, LocalTime hora);

	@Query(value = "SELECT * FROM tb_reserva WHERE restaurante_id = ?1", nativeQuery = true)
	List<ReservaEntity> encontrarReservasPorRestaurante(Long restauranteId);

}

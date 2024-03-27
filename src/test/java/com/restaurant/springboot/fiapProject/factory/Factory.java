package com.restaurant.springboot.fiapProject.factory;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import com.restaurant.springboot.fiapProject.adapter.input.entities.ComentarioRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.RestauranteRequest;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ComentarioEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;

public class Factory {

	public static RestauranteEntity createRestauranteEntity() {
		RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome("Nayumi");
		restaurante.setCidade("Praia Grande");
		restaurante.setEndereco("Rua Itapora");
		restaurante.setGastronomia("Japonesa");
		restaurante.setHorarioAbertura("10:00");
		restaurante.setHorarioFechamento("20:00");
		restaurante.setMesasDisponiveis(20);

		return restaurante;

	}
	
	public static RestauranteRequest createRestauranteRequest() {
		RestauranteRequest restaurante = new RestauranteRequest();
		restaurante.setNome("Nayumi");
		restaurante.setCidade("Praia Grande");
		restaurante.setEndereco("Rua Itapora");
		restaurante.setGastronomia("Japonesa");
		restaurante.setHorarioAbertura("10:00");
		restaurante.setHorarioFechamento("20:00");
		restaurante.setMesasDisponiveis(20);

		return restaurante;

	}
	
	public static RestauranteRequest createRestauranteRequestComAlgumParametroNulo() {
		RestauranteRequest restaurante = new RestauranteRequest();
		restaurante.setNome("Nayumi");
		restaurante.setCidade("Praia Grande");
		restaurante.setGastronomia("Japonesa");
		restaurante.setHorarioAbertura("10:00");
		restaurante.setHorarioFechamento("20:00");
		restaurante.setMesasDisponiveis(20);

		return restaurante;

	}
	
	public static RestauranteEntity createRestauranteEntityWithId() {
		RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome("Nayumi");
		restaurante.setCidade("Praia Grande");
		restaurante.setEndereco("Rua Itapora");
		restaurante.setGastronomia("Japonesa");
		restaurante.setHorarioAbertura("10:00");
		restaurante.setHorarioFechamento("20:00");
		restaurante.setMesasDisponiveis(20);
		restaurante.setId(1L);

		return restaurante;

	}
	
	public static Optional<RestauranteEntity> createRestauranteEntityOptional() {
		RestauranteEntity restaurante = new RestauranteEntity();
		restaurante.setNome("Nayumi");
		restaurante.setCidade("Praia Grande");
		restaurante.setEndereco("Rua Itapora");
		restaurante.setGastronomia("Japonesa");
		restaurante.setHorarioAbertura("10:00");
		restaurante.setHorarioFechamento("20:00");
		restaurante.setMesasDisponiveis(20);

		return Optional.of(restaurante);

	}

	public static ReservaEntity createReservaEntity() {
		ReservaEntity reservaEntity = new ReservaEntity();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData(LocalDate.of(2024, 03, 22));
		reservaEntity.setHora(LocalTime.of(18, 30));
		reservaEntity.setQuantidadePessoas(4);
		reservaEntity.setReservaCancelada(false);
		reservaEntity.setRestaurante(createRestauranteEntity());
		return reservaEntity;
	}
	
	public static ReservaEntity createReservaEntityDataAmanha() {
		ReservaEntity reservaEntity = new ReservaEntity();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData(LocalDate.of(2024, 03, 22));
		reservaEntity.setHora(LocalTime.of(18, 30));
		reservaEntity.setQuantidadePessoas(4);
		reservaEntity.setReservaCancelada(false);
		reservaEntity.setRestaurante(createRestauranteEntity());
		return reservaEntity;
	}
	
	public static ReservaRequest createReservaRequest() {
		ReservaRequest reservaEntity = new ReservaRequest();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData("24/03/2024");
		reservaEntity.setHora("18:30");
		reservaEntity.setQuantidadePessoas(4);
		reservaEntity.setRestauranteNome("Nayumi");
		return reservaEntity;
	}
	
	public static ReservaRequest createReservaRequestComIdIncorreto() {
		ReservaRequest reservaEntity = new ReservaRequest();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData("24/03/2024");
		reservaEntity.setHora("18:30");
		reservaEntity.setQuantidadePessoas(4);
		reservaEntity.setRestauranteId(3L);	
		return reservaEntity;
	}
	
	
	public static ReservaRequest createReservaRequestComNomeIncorreto() {
		ReservaRequest reservaEntity = new ReservaRequest();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData("24/03/2024");
		reservaEntity.setHora("18:30");
		reservaEntity.setQuantidadePessoas(4);
		reservaEntity.setRestauranteNome("Nayu");
		return reservaEntity;
	}
	
	
	
	public static Optional<ReservaEntity> createReservaEntityOptional() {
		ReservaEntity reservaEntity = new ReservaEntity();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData(LocalDate.of(2024, 3, 22));
		reservaEntity.setHora(LocalTime.of(18, 30));
		reservaEntity.setQuantidadePessoas(4);
		reservaEntity.setReservaCancelada(true);
		reservaEntity.setRestaurante(createRestauranteEntity());
		return Optional.of(reservaEntity);
	}
	
	public static Optional<ReservaEntity> createReservaEntityOptionalRestauranteWithId() {
		ReservaEntity reservaEntity = new ReservaEntity();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData(LocalDate.now());
		reservaEntity.setHora(LocalTime.of(18, 30));
		reservaEntity.setQuantidadePessoas(4);
		reservaEntity.setReservaCancelada(true);
		reservaEntity.setRestaurante(createRestauranteEntityWithId());
		return Optional.of(reservaEntity);
	}

	public static ReservaEntity createReservaCanceladaEntity() {
		ReservaEntity reservaEntity = new ReservaEntity();
		reservaEntity.setNomeUsuario("Osvaldo");
		reservaEntity.setTelefone("13 981549851");
		reservaEntity.setEmail("osv@outlook.com");
		reservaEntity.setData(LocalDate.now());
		reservaEntity.setHora(LocalTime.now());
		reservaEntity.setQuantidadePessoas(10);
		reservaEntity.setReservaCancelada(true);
		reservaEntity.setRestaurante(createRestauranteEntity());
		return reservaEntity;
	}
	
	public static ComentarioEntity createComentarioEntity() {
		ComentarioEntity comentarioEntity = new ComentarioEntity();
		comentarioEntity.setComentario("Muito bom");
		comentarioEntity.setAvaliacao(10);
		comentarioEntity.setNomeCliente("Osvaldo");
		comentarioEntity.setReserva(createReservaEntity());
		
		return comentarioEntity;
	}
	
	public static ComentarioRequest createComentarioRequest() {
		ComentarioRequest comentarioEntity = new ComentarioRequest();
		comentarioEntity.setComentario("Muito bom");
		comentarioEntity.setAvaliacao(10);
		comentarioEntity.setNomeCliente("Osvaldo");
		comentarioEntity.setReservaId(1L);
		
		return comentarioEntity;
	}
	
	public static ComentarioRequest createComentarioRequestComIdReservaInexistente() {
		ComentarioRequest comentarioEntity = new ComentarioRequest();
		comentarioEntity.setComentario("Muito bom");
		comentarioEntity.setAvaliacao(10);
		comentarioEntity.setNomeCliente("Osvaldo");
		comentarioEntity.setReservaId(2L);
		
		return comentarioEntity;
	}
	
	public static ComentarioRequest createComentarioRequestComValoresNulos() {
		ComentarioRequest comentarioEntity = new ComentarioRequest();
		comentarioEntity.setComentario("Muito bom");
		comentarioEntity.setAvaliacao(10);
		comentarioEntity.setReservaId(1L);
		
		return comentarioEntity;
	}
	
	public static Optional<ComentarioEntity> createComentarioEntityOptional() {
		ComentarioEntity comentarioEntity = new ComentarioEntity();
		comentarioEntity.setComentario("Muito bom");
		comentarioEntity.setAvaliacao(10);
		comentarioEntity.setNomeCliente("Osvaldo");
		comentarioEntity.setReserva(createReservaEntity());
		
		return Optional.of(comentarioEntity);
	}
	
	public static Restaurante createRestaurante() {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome("Nayumi");
		restaurante.setCidade("Praia Grande");
		restaurante.setEndereco("Rua Itapora");
		restaurante.setGastronomia("Japonesa");
		restaurante.setHorarioAbertura("10:00");
		restaurante.setHorarioFechamento("20:00");
		restaurante.setMesasDisponiveis(20);

		return restaurante;
	}
	

	public static Reserva createReserva() {
		Reserva reserva = new Reserva();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData(LocalDate.now());
		reserva.setHora(LocalTime.of(18, 30));
		reserva.setQuantidadePessoas(4);
		reserva.setReservaCancelada(false);
		reserva.setRestauranteId(null);
		reserva.setRestauranteNome("Nayumi");
		return reserva;
	}
	
	public static Reserva createReservaComDadosNulos() {
		Reserva reserva = new Reserva();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setData(LocalDate.now());
		reserva.setHora(LocalTime.of(18, 30));
		reserva.setQuantidadePessoas(4);
		reserva.setReservaCancelada(false);
		reserva.setRestauranteId(null);
		reserva.setRestauranteNome("Nayumi");
		return reserva;
	}
	
	public static Reserva createReservaComTrue() {
		Reserva reserva = new Reserva();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData(LocalDate.now());
		reserva.setHora(LocalTime.of(18, 30));
		reserva.setQuantidadePessoas(4);
		reserva.setReservaCancelada(true);
		reserva.setRestauranteId(null);
		reserva.setRestauranteNome("Nayumi");
		return reserva;
	}
	
	public static Reserva createReservaComReservaId() {
		Reserva reserva = new Reserva();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData(LocalDate.now());
		reserva.setHora(LocalTime.of(18, 30));
		reserva.setQuantidadePessoas(4);
		reserva.setReservaCancelada(false);
		reserva.setRestauranteId(null);
		reserva.setReservaId(1L);
		reserva.setRestauranteNome("Nayumi");
		return reserva;
	}
	
	public static Reserva createReservaComId() {
		Reserva reserva = new Reserva();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData(LocalDate.of(2024, 03, 24));
		reserva.setHora(LocalTime.of(18, 30));
		reserva.setQuantidadePessoas(4);
		reserva.setReservaCancelada(false);
		reserva.setRestauranteId(1L);
		return reserva;
	}
	

	public static ReservaRequest createReservaRequestComId() {
		ReservaRequest reserva = new ReservaRequest();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData("25/03/2024");
		reserva.setHora("18:00");
		reserva.setQuantidadePessoas(4);
		reserva.setRestauranteId(1L);
		return reserva;
	}
	
	public static ReservaRequest createReservaRequestComIdMaiorQueOMaximo() {
		ReservaRequest reserva = new ReservaRequest();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData("25/03/2024");
		reserva.setHora("18:00");
		reserva.setQuantidadePessoas(5);
		reserva.setRestauranteId(1L);
		return reserva;
	}
	
	
	public static ReservaRequest createReservaRequestComValoresNulos() {
		ReservaRequest reserva = new ReservaRequest();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData("25/03/2024");
		reserva.setHora("18:00");
		reserva.setRestauranteId(1L);
		return reserva;
	}
	
	public static ReservaRequest createReservaRequestComNome() {
		ReservaRequest reserva = new ReservaRequest();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData("25/03/2024");
		reserva.setHora("18:00");
		reserva.setQuantidadePessoas(4);
		reserva.setRestauranteNome("Nayumi");
		return reserva;
	}
	
	public static Reserva createReservaAtualizada() {
		Reserva reserva = new Reserva();
		reserva.setNomeUsuario("Osvaldo");
		reserva.setTelefone("13 981549851");
		reserva.setEmail("osv@outlook.com");
		reserva.setData(LocalDate.now());
		reserva.setHora(LocalTime.of(18, 30));
		reserva.setQuantidadePessoas(4);
		reserva.setReservaCancelada(true);
		reserva.setRestauranteId(1L);
		return reserva;
	}
	
	public static Comentario createComentario() {
		Comentario comentario = new Comentario();
		comentario.setComentario("Muito bom");
		comentario.setAvaliacao(10);
		comentario.setNomeCliente("Osvaldo");
		comentario.setReservaId(1L);
		
		return comentario;
	}

}

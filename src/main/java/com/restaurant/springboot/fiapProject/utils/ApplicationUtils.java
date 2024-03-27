package com.restaurant.springboot.fiapProject.utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.restaurant.springboot.fiapProject.adapter.input.entities.ComentarioRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaAtualizaRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ReservaRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.RestauranteResponse;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ComentarioEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.ReservaEntity;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.entities.Reserva;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;

public class ApplicationUtils {

	public static final Integer QUANTIDADE_MAXIMA = 4;

	public static List<RestauranteResponse> toRestauranteResponse(List<Restaurante> lista) {
		List<RestauranteResponse> response = new ArrayList<>();

		for (Restaurante restaurante : lista) {
			response.add(new RestauranteResponse(restaurante));
		}

		return response;
	}

	public static List<Restaurante> toRestaurante(List<RestauranteEntity> lista) {
		List<Restaurante> response = new ArrayList<>();

		for (RestauranteEntity restaurante : lista) {
			response.add(new Restaurante(restaurante));
		}

		return response;
	}

	public static Comentario toComentario(ComentarioEntity comentarioEntity) {
		Comentario comentario = new Comentario();
		comentario.setAvaliacao(comentarioEntity.getAvaliacao());
		comentario.setComentario(comentarioEntity.getComentario());
		comentario.setComentarioId(comentarioEntity.getComentarioId());
		comentario.setNomeCliente(comentarioEntity.getNomeCliente());
		comentario.setReservaId(comentarioEntity.getReserva().getReservaId());
		return comentario;
	}

	public static Comentario toComentario(ComentarioRequest comentarioRequest) {
		Comentario comentario = new Comentario();
		if(comentarioRequest.getAvaliacao() != null) {
		comentario.setAvaliacao(comentarioRequest.getAvaliacao());
		}
		if(comentarioRequest.getComentario() != null) {
		comentario.setComentario(comentarioRequest.getComentario());
		}
		if(comentarioRequest.getNomeCliente() != null) {
		comentario.setNomeCliente(comentarioRequest.getNomeCliente());
		}
		if(comentarioRequest.getReservaId() != null) {
		comentario.setReservaId(comentarioRequest.getReservaId());
		}
		return comentario;
	}

	public static Reserva toReserva(ReservaEntity reservaEntity) {
		Reserva reserva = new Reserva();
		reserva.setData(reservaEntity.getData());
		reserva.setHora(reservaEntity.getHora());
		reserva.setEmail(reservaEntity.getEmail());
		reserva.setTelefone(reservaEntity.getTelefone());
		reserva.setNomeUsuario(reservaEntity.getNomeUsuario());
		reserva.setQuantidadePessoas(reservaEntity.getQuantidadePessoas());
		reserva.setReservaId(reservaEntity.getReservaId());
		if (reservaEntity.getRestaurante() != null) {
			reserva.setRestauranteId(reservaEntity.getRestaurante().getId());
			reserva.setRestauranteNome(reservaEntity.getRestaurante().getNome());
		}
		reserva.setReservaCancelada(reservaEntity.getReservaCancelada());

		return reserva;
	}

	public static Reserva toReserva(ReservaRequest reservaRequest) {
		Reserva reserva = new Reserva();
		if(reservaRequest.getData() != null) {
		reserva.setData(localDateFormatter(reservaRequest.getData()));
		}
		if(reservaRequest.getEmail() != null) {
		reserva.setEmail(reservaRequest.getEmail());
		}
		if(reservaRequest.getTelefone() != null) {
		reserva.setTelefone(reservaRequest.getTelefone());
		}
		if(reservaRequest.getHora() != null) {
		reserva.setHora(localTimeFormatter(reservaRequest.getHora()));
		}
		if(reservaRequest.getNomeUsuario() != null) {
		reserva.setNomeUsuario(reservaRequest.getNomeUsuario());
		}
		if(reservaRequest.getQuantidadePessoas() != null) {
		reserva.setQuantidadePessoas(reservaRequest.getQuantidadePessoas());
		}
		if (reservaRequest.getRestauranteId() != null) {
			reserva.setRestauranteId(reservaRequest.getRestauranteId());
		}
		if (reservaRequest.getRestauranteNome() != null) {
			reserva.setRestauranteNome(reservaRequest.getRestauranteNome());
		}
		reserva.setReservaCancelada(false);
		return reserva;
	}

	public static Reserva toReserva(ReservaAtualizaRequest request) {
		Reserva atualizaReserva = new Reserva();
		if (request.getData() != null) {
			atualizaReserva.setData(localDateFormatter(request.getData()));

		}
		if (request.getEmail() != null) {
			atualizaReserva.setEmail(request.getEmail());
		}
		if (request.getHora() != null) {
			atualizaReserva.setHora(localTimeFormatter(request.getHora()));
		}
		if (request.getNomeUsuario() != null) {
			atualizaReserva.setNomeUsuario(request.getNomeUsuario());
		}
		if (request.getReservaCancelada() != null) {
			atualizaReserva.setReservaCancelada(request.getReservaCancelada());
		}
		if (request.getRestauranteId() != null) {
			atualizaReserva.setRestauranteId(request.getRestauranteId());
		}
		if (request.getRestauranteNome() != null) {
			atualizaReserva.setRestauranteNome(request.getRestauranteNome());
		}
		if (request.getTelefone() != null) {
			atualizaReserva.setTelefone(request.getTelefone());
		}

		return atualizaReserva;

	}

	public static ReservaEntity atualizaReservaEntity(ReservaEntity reservaEntity, Reserva reserva) {
		if (reserva.getData() != null) {
			reservaEntity.setData(reserva.getData());
		}
		if (reserva.getHora() != null) {
			reservaEntity.setHora(reserva.getHora());
		}
		if (reserva.getEmail() != null) {
			reservaEntity.setEmail(reserva.getEmail());
		}
		if (reserva.getNomeUsuario() != null) {
			reservaEntity.setNomeUsuario(reserva.getNomeUsuario());
		}
		if (reserva.getReservaCancelada() != null) {
			reservaEntity.setReservaCancelada(reserva.getReservaCancelada());
		}
		if (reserva.getTelefone() != null) {
			reservaEntity.setTelefone(reserva.getTelefone());
		}
		return reservaEntity;

	}

	public static LocalDate localDateFormatter(String dataString) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate data = LocalDate.parse(dataString, formatter);
		return data;
	}

	public static LocalTime localTimeFormatter(String time) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		LocalTime hora = LocalTime.parse(time, formatter);
		return hora;

	}
	
	public static Long parseLong(String str) {
		Long lng = Long.parseLong(str);
		return lng;
	}

}

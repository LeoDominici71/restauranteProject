package com.restaurant.springboot.fiapProject.adapter.input.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import com.restaurant.springboot.fiapProject.core.entities.Reserva;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReservaResponse {
	
	private Long reservaId;
	private String nomeUsuario;
	private String telefone;
	private String email;
	private LocalDate data;
	private LocalTime hora;
	private Integer quantidadePessoas; 
	private Long restauranteId;
	private String restauranteNome;
	private Boolean reservaCancelada;
	
	public ReservaResponse(Reserva reserva) {
		this.reservaId = reserva.getReservaId();
		this.nomeUsuario = reserva.getNomeUsuario();
		this.telefone = reserva.getTelefone();
		this.email = reserva.getEmail();
		this.data = reserva.getData();
		this.hora = reserva.getHora();
		this.quantidadePessoas = reserva.getQuantidadePessoas();
		if(reserva.getRestauranteId() != null) {
		this.restauranteId = reserva.getRestauranteId();
		}
		if(reserva.getRestauranteNome() != null) {
		this.restauranteNome = reserva.getRestauranteNome();
		}
		this.reservaCancelada = reserva.getReservaCancelada();
		
		
	}

}

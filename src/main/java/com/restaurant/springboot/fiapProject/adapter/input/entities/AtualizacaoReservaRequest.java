package com.restaurant.springboot.fiapProject.adapter.input.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AtualizacaoReservaRequest {
	private String nomeUsuario;
	private LocalDate data;
	private LocalTime hora;
	private Integer quantidadePessoas;
	private Long restauranteId;
	private String restauranteNome;
	private Boolean confirmacaoReserva;

}

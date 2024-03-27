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
public class ReservaRequest {
	
	private String nomeUsuario;
	private String telefone;
	private String email;
	private String data;
	private String hora;
	private Integer quantidadePessoas; 
	private Long restauranteId;
	private String restauranteNome;

}

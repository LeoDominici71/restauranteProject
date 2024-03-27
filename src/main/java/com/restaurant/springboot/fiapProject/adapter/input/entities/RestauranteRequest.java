package com.restaurant.springboot.fiapProject.adapter.input.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteRequest {
	
	private String nome;
	private String endereco;
	private String cidade;
	private String gastronomia;
	private String horarioAbertura;
	private String horarioFechamento;
	private Integer mesasDisponiveis;
	

}

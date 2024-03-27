package com.restaurant.springboot.fiapProject.adapter.input.entities;

import com.restaurant.springboot.fiapProject.core.entities.Restaurante;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RestauranteResponse {

	private Long id;
	private String nome;
	private String endereco;
	private String cidade;
	private String gastronomia;
	private String horarioAbertura;
	private String horarioFechamento;
	private Integer mesasDisponiveis;

	public RestauranteResponse() {
		super();
	}
	
	public RestauranteResponse(Restaurante restaurante) {
		this.id = restaurante.getId();
		this.nome = restaurante.getNome();
		this.endereco = restaurante.getEndereco();
		this.cidade = restaurante.getCidade();
		this.gastronomia = restaurante.getGastronomia();
		this.horarioAbertura = restaurante.getHorarioAbertura();
		this.horarioFechamento = restaurante.getHorarioFechamento();
		this.mesasDisponiveis = restaurante.getMesasDisponiveis();
	}

}

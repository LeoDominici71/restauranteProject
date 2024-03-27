package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity;


import com.restaurant.springboot.fiapProject.core.entities.Restaurante;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "tb_restaurant")
@Getter
@Setter
@ToString
public class RestauranteEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	private String endereco;
	private String cidade;
	private String gastronomia;
	private String horarioAbertura;
	private String horarioFechamento;
	private Integer mesasDisponiveis;
	
	public RestauranteEntity() {
		super();
	}

	public RestauranteEntity(Long id, String nome, String endereco, String cidade, String gastronomia,
			String horarioAbertura, String horarioFechamento, Integer mesasDisponiveis) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.cidade = cidade;
		this.gastronomia = gastronomia;
		this.horarioAbertura = horarioAbertura;
		this.horarioFechamento = horarioFechamento;
		this.mesasDisponiveis = mesasDisponiveis;
	}
	
	public RestauranteEntity(Restaurante restaurante) {
		
		this.nome = restaurante.getNome();
		this.endereco = restaurante.getEndereco();
		this.cidade = restaurante.getCidade();
		this.gastronomia = restaurante.getGastronomia();
		this.horarioAbertura = restaurante.getHorarioAbertura();
		this.horarioFechamento = restaurante.getHorarioFechamento();
		this.mesasDisponiveis = restaurante.getMesasDisponiveis();		
		
	}
	
	
	
	


}

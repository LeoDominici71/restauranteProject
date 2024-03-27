package com.restaurant.springboot.fiapProject.core.entities;

import java.util.Objects;

import com.restaurant.springboot.fiapProject.adapter.input.entities.RestauranteRequest;
import com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity.RestauranteEntity;

public class Restaurante {

	private Long id;
	private String nome;
	private String endereco;
	private String cidade;
	private String gastronomia;
	private String horarioAbertura;
	private String horarioFechamento;
	private Integer mesasDisponiveis;

	public Restaurante() {
		super();
	}
	
	public Restaurante(RestauranteRequest request) {
		this.nome = request.getNome();
		this.endereco = request.getEndereco();
		this.cidade = request.getCidade();
		this.gastronomia = request.getGastronomia();
		this.horarioAbertura = request.getHorarioAbertura();
		this.horarioFechamento = request.getHorarioFechamento();
		this.mesasDisponiveis = request.getMesasDisponiveis();
	}
	
	public Restaurante(RestauranteEntity request) {
		this.id = request.getId();
		this.nome = request.getNome();
		this.endereco = request.getEndereco();
		this.cidade = request.getCidade();
		this.gastronomia = request.getGastronomia();
		this.horarioAbertura = request.getHorarioAbertura();
		this.horarioFechamento = request.getHorarioFechamento();
		this.mesasDisponiveis = request.getMesasDisponiveis();
	}

	public Restaurante(Long id, String nome, String endereco, String cidade, String gastronomia, String horarioAbertura,
			String horarioFechamento, Integer mesasDisponiveis) {
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
	
	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getGastronomia() {
		return gastronomia;
	}

	public void setGastronomia(String gastronomia) {
		this.gastronomia = gastronomia;
	}

	public String getHorarioAbertura() {
		return horarioAbertura;
	}

	public void setHorarioAbertura(String horarioAbertura) {
		this.horarioAbertura = horarioAbertura;
	}

	public String getHorarioFechamento() {
		return horarioFechamento;
	}

	public void setHorarioFechamento(String horarioFechamento) {
		this.horarioFechamento = horarioFechamento;
	}

	public Integer getMesasDisponiveis() {
		return mesasDisponiveis;
	}

	public void setMesasDisponiveis(Integer mesasDisponiveis) {
		this.mesasDisponiveis = mesasDisponiveis;
	}

	@Override
	public String toString() {
		return "Restaurante [id=" + id + ", nome=" + nome + ", endereço=" + endereco + ", cidade=" + cidade
				+ ", tipoCozinha=" + gastronomia + ", horarioAbertura=" + horarioAbertura + ", horarioFechamento="
				+ horarioFechamento + ", mesasDisponiveis=" + mesasDisponiveis + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Restaurante other = (Restaurante) obj;
		return Objects.equals(id, other.id);
	}

	

}
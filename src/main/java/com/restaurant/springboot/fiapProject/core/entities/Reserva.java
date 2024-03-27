package com.restaurant.springboot.fiapProject.core.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Reserva {

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

	public Reserva() {
		super();
	}

	
	
	public Reserva(Long reservaId, String nomeUsuario, String telefone, String email, LocalDate data, LocalTime hora,
			Integer quantidadePessoas, Long restauranteId, String restauranteNome, Boolean reservaCancelada) {
		super();
		this.reservaId = reservaId;
		this.nomeUsuario = nomeUsuario;
		this.telefone = telefone;
		this.email = email;
		this.data = data;
		this.hora = hora;
		this.quantidadePessoas = quantidadePessoas;
		this.restauranteId = restauranteId;
		this.restauranteNome = restauranteNome;
		this.reservaCancelada = reservaCancelada;
	}



	public Long getReservaId() {
		return reservaId;
	}

	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public LocalTime getHora() {
		return hora;
	}

	public void setHora(LocalTime hora) {
		this.hora = hora;
	}

	public Integer getQuantidadePessoas() {
		return quantidadePessoas;
	}

	public void setQuantidadePessoas(Integer quantidadePessoas) {
		this.quantidadePessoas = quantidadePessoas;
	}

	public String getRestauranteNome() {
		return restauranteNome;
	}

	public void setRestauranteNome(String restauranteNome) {
		this.restauranteNome = restauranteNome;
	}

	public Long getRestauranteId() {
		return restauranteId;
	}

	public void setRestauranteId(Long restauranteId) {
		this.restauranteId = restauranteId;
	}

	
	

	public Boolean getReservaCancelada() {
		return reservaCancelada;
	}



	public void setReservaCancelada(Boolean reservaCancelada) {
		this.reservaCancelada = reservaCancelada;
	}



	
	@Override
	public String toString() {
		return "Reserva [reservaId=" + reservaId + ", nomeUsuario=" + nomeUsuario + ", telefone=" + telefone
				+ ", email=" + email + ", data=" + data + ", hora=" + hora + ", quantidadePessoas=" + quantidadePessoas
				+ ", restauranteId=" + restauranteId + ", restauranteNome=" + restauranteNome + ", reservaCancelada="
				+ reservaCancelada + "]";
	}



	@Override
	public int hashCode() {
		return Objects.hash(reservaId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reserva other = (Reserva) obj;
		return Objects.equals(reservaId, other.reservaId);
	}

}

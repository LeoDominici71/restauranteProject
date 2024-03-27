package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import com.restaurant.springboot.fiapProject.core.entities.Reserva;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "tb_reserva")
public class ReservaEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long reservaId;
	private String nomeUsuario;
	private String telefone;
	private String email;
	private LocalDate data;
	private LocalTime hora;
	private Integer quantidadePessoas;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="restaurante_id")
	private RestauranteEntity restaurante;
	private Boolean reservaCancelada;
	
	public ReservaEntity(Reserva reserva) {
		this.nomeUsuario = reserva.getNomeUsuario();
		this.telefone = reserva.getTelefone();
		this.email = reserva.getEmail();
		this.data = reserva.getData();
		this.hora = reserva.getHora();
		this.quantidadePessoas = reserva.getQuantidadePessoas();
		this.reservaCancelada = reserva.getReservaCancelada();
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
		ReservaEntity other = (ReservaEntity) obj;
		return Objects.equals(reservaId, other.reservaId);
	}
	
	
	

}

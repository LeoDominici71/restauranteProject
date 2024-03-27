package com.restaurant.springboot.fiapProject.adapter.output.jpaH2Database.entity;

import java.util.Objects;

import com.restaurant.springboot.fiapProject.core.entities.Comentario;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "tb_comentario")
public class ComentarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long comentarioId;
	private String nomeCliente;
	private String comentario;
	private Integer avaliacao;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="reserva_id")
	private ReservaEntity reserva;
	@Override
	public int hashCode() {
		return Objects.hash(comentarioId);
	}
	
	public ComentarioEntity(Comentario comentario) {
		this.nomeCliente = comentario.getNomeCliente();
		this.comentario = comentario.getComentario();
		this.avaliacao = comentario.getAvaliacao();
	}
	
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComentarioEntity other = (ComentarioEntity) obj;
		return Objects.equals(comentarioId, other.comentarioId);
	}

	
	

}

package com.restaurant.springboot.fiapProject.adapter.input.entities;

import com.restaurant.springboot.fiapProject.core.entities.Comentario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComentarioReesponse {

	private Long comentarioId;
	private String nomeCliente;
	private String comentario;
	private Integer avaliacao;
	private Long reservaId;
	
	public ComentarioReesponse(Comentario comentario) {
		this.comentarioId = comentario.getComentarioId();
		this.nomeCliente = comentario.getNomeCliente();
		this.comentario = comentario.getComentario();
		this.avaliacao = comentario.getAvaliacao();
		this.reservaId = comentario.getReservaId();
	}

}

package com.restaurant.springboot.fiapProject.adapter.input.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComentarioRequest {

	private Long comentarioId;
	private String nomeCliente;
	private String comentario;
	private Integer avaliacao;
	private Long reservaId;

}

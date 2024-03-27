package com.restaurant.springboot.fiapProject.core.useCase.comentarios;

import com.restaurant.springboot.fiapProject.core.ComentarioRepository;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;

public class RegistroComentario {

	private final ComentarioRepository repository;

	public RegistroComentario(ComentarioRepository repository) {
		this.repository = repository;
	}

	public Comentario registroComentario(Comentario comentario) {

		if(comentario.getComentario() == null || comentario.getNomeCliente() == null || comentario.getAvaliacao() == null) {
			throw new IllegalArgumentException("Não pode haver campos nulos no preenchimento do comentario");
		}
		return repository.registroComentario(comentario);
	}

}

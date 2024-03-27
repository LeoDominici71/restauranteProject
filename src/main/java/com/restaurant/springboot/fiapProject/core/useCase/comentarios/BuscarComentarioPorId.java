package com.restaurant.springboot.fiapProject.core.useCase.comentarios;

import com.restaurant.springboot.fiapProject.core.ComentarioRepository;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;

public class BuscarComentarioPorId {
	

	private final ComentarioRepository repository;
	
	public BuscarComentarioPorId(ComentarioRepository repository) {
		this.repository = repository;
	}
	
	public Comentario buscarComentarioPorId(Long id) {
		return repository.buscarComentarioPorId(id);
	
	}
	
	

}

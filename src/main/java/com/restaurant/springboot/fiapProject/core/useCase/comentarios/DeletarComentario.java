package com.restaurant.springboot.fiapProject.core.useCase.comentarios;

import com.restaurant.springboot.fiapProject.core.ComentarioRepository;

public class DeletarComentario {
	
private final ComentarioRepository repository;
	
	public DeletarComentario(ComentarioRepository repository) {
		this.repository = repository;
	}
	
	public void deletarComentario(Long id) {
		repository.deletarComentario(id);
	}

}

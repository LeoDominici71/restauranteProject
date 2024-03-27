package com.restaurant.springboot.fiapProject.core;

import com.restaurant.springboot.fiapProject.core.entities.Comentario;

public interface ComentarioRepository {
	
	Comentario registroComentario(Comentario comentario);
	
	Comentario buscarComentarioPorId(Long comentarioId);
	
	void deletarComentario(Long comentarioId);

}

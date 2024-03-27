package com.restaurant.springboot.fiapProject.adapter.input;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restaurant.springboot.fiapProject.adapter.input.entities.ComentarioReesponse;
import com.restaurant.springboot.fiapProject.adapter.input.entities.ComentarioRequest;
import com.restaurant.springboot.fiapProject.core.entities.Comentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.BuscarComentarioPorId;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.DeletarComentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.RegistroComentario;
import com.restaurant.springboot.fiapProject.utils.ApplicationUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/comentario")
public class ComentarioControler {

	private final BuscarComentarioPorId buscarComentarioPorId;
	private final DeletarComentario deletarComentario;
	private final RegistroComentario registroComentario;

	@Autowired
	public ComentarioControler(BuscarComentarioPorId buscarComentarioPorId, DeletarComentario deletarComentario,
			RegistroComentario registroComentario) {
		this.buscarComentarioPorId = buscarComentarioPorId;
		this.deletarComentario = deletarComentario;
		this.registroComentario = registroComentario;
	}

	@Operation(description = "Deve registrar um comentario")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Comentario criado"),
			@ApiResponse(responseCode = "404", description = "Reserva inexistente"),
			@ApiResponse(responseCode = "400", description = "Parametros nulos")

	})
	@PostMapping
	public ResponseEntity<ComentarioReesponse> registroComentario(@RequestBody ComentarioRequest request) {
		Comentario comentario = registroComentario.registroComentario(ApplicationUtils.toComentario(request));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(comentario.getComentarioId()).toUri();
		return ResponseEntity.created(uri).body(new ComentarioReesponse(comentario));
	}

	@Operation(description = "Deve buscar um comentario por id")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Comentario encontrado"),
			@ApiResponse(responseCode = "404", description = "comentario inexistente"),

	})
	@GetMapping(value = "{comentarioId}")
	public ResponseEntity<ComentarioReesponse> buscaComentarioPorId(@PathVariable Long comentarioId) {
		Comentario comentario = buscarComentarioPorId.buscarComentarioPorId(comentarioId);
		return ResponseEntity.ok().body(new ComentarioReesponse(comentario));
	}

	@Operation(description = "Deve buscar deletar um comentario por id")
	@ApiResponses({ @ApiResponse(responseCode = "204", description = "Comentario deletado"),
			@ApiResponse(responseCode = "404", description = "Comentario inexistente"),

	})

	@DeleteMapping(value = "/{comentarioId}")
	public ResponseEntity<?> deletarComentario(@PathVariable Long comentarioId) {
		deletarComentario.deletarComentario(comentarioId);
		return ResponseEntity.noContent().build();

	}

}

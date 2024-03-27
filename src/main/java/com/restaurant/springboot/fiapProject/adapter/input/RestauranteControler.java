package com.restaurant.springboot.fiapProject.adapter.input;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restaurant.springboot.fiapProject.adapter.input.entities.RestauranteRequest;
import com.restaurant.springboot.fiapProject.adapter.input.entities.RestauranteResponse;
import com.restaurant.springboot.fiapProject.core.entities.Restaurante;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.BuscarRestaurantes;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.CadastroRestaurantes;
import com.restaurant.springboot.fiapProject.utils.ApplicationUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteControler {

	private final CadastroRestaurantes service;
	
	private final BuscarRestaurantes buscaDeRestaurante;

	@Autowired
	public RestauranteControler(CadastroRestaurantes service, BuscarRestaurantes buscaDeRestaurante) {
		this.service = service;
		this.buscaDeRestaurante = buscaDeRestaurante;
	}
	@Operation(description = "Deve registrar um Restaurante")
	@ApiResponses({ @ApiResponse(responseCode = "201", description = "Restaurante criado"),
			@ApiResponse(responseCode = "404", description = "Restaurante inexistente"),
			@ApiResponse(responseCode = "400", description = "Parametros nulos")

	})
	@PostMapping
	public ResponseEntity<RestauranteResponse> cadastrarRestaurante(@RequestBody RestauranteRequest request) {
		Restaurante restaurante = service.registroRestaurantes(new Restaurante(request));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(restaurante.getId())
				.toUri();
		return ResponseEntity.created(uri).body(new RestauranteResponse(restaurante));

	}
	@Operation(description = "Deve buscar um restaurante por nome")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "restaurante encontrado"),

	})
	@GetMapping(value = "/buscaPorNome")
	public ResponseEntity<List<RestauranteResponse>> encontrarRestaurantePorNome(
			@RequestParam String nome) {
      List<Restaurante> list = buscaDeRestaurante.buscarRestaurantePorNome(nome);
      return ResponseEntity.ok().body(ApplicationUtils.toRestauranteResponse(list));
	}
	@Operation(description = "Deve buscar um restaurante por cidade")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "restaurante encontrado"),

	})
	@GetMapping(value = "/buscaPorCidade")
	public ResponseEntity<List<RestauranteResponse>> encontrarRestaurantePorCidade(
			@RequestParam String cidade) {
		 List<Restaurante> list = buscaDeRestaurante.buscarRestaurantePorCidade(cidade);
	      return ResponseEntity.ok().body(ApplicationUtils.toRestauranteResponse(list));

	}
	@Operation(description = "Deve buscar um restaurante por cozinha")
	@ApiResponses({ @ApiResponse(responseCode = "200", description = "restaurante encontrado"),

	})
	@GetMapping(value = "/buscaPorCozinha")
	public ResponseEntity<List<RestauranteResponse>> encontrarRestaurantePorCozinha(
			@RequestParam String gastronomia) {
		 List<Restaurante> list = buscaDeRestaurante.buscarRestaurantePorCozinha(gastronomia);
	      return ResponseEntity.ok().body(ApplicationUtils.toRestauranteResponse(list));

	}
	
	

}

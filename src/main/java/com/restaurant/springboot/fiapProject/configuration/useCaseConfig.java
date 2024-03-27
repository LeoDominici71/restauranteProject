package com.restaurant.springboot.fiapProject.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restaurant.springboot.fiapProject.core.ComentarioRepository;
import com.restaurant.springboot.fiapProject.core.ReservaRepository;
import com.restaurant.springboot.fiapProject.core.RestauranteRepository;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.AvaliarComentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.BuscarComentarioPorId;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.DeletarComentario;
import com.restaurant.springboot.fiapProject.core.useCase.comentarios.RegistroComentario;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AtualizaReserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.AvaliadorDeReservas;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.BuscaReservaPorId;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.DeletarReserva;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaDeReservaPorDiaHora;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.ListaReservaPorRestaurante;
import com.restaurant.springboot.fiapProject.core.useCase.reserva.RegistroReserva;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.BuscarRestaurantes;
import com.restaurant.springboot.fiapProject.core.useCase.restaurante.CadastroRestaurantes;

@Configuration
public class useCaseConfig {

	@Bean
	public CadastroRestaurantes cadastroRestaurantes(RestauranteRepository restauranteRepository) {
		return new CadastroRestaurantes(restauranteRepository);
	}

	@Bean
	public BuscarRestaurantes buscarRestaurantes(RestauranteRepository restauranteRepository) {
		return new BuscarRestaurantes(restauranteRepository);
	}

	@Bean
	public BuscaReservaPorId buscarReservaPorId(ReservaRepository reservaRepository) {
		return new BuscaReservaPorId(reservaRepository);
	}

	@Bean
	public DeletarReserva deletarReserva(ReservaRepository reservaRepository) {
		return new DeletarReserva(reservaRepository);
	}

	@Bean
	public RegistroReserva registroReserva(ReservaRepository reservaRepository) {
		return new RegistroReserva(reservaRepository);
	}

	@Bean
	public RegistroComentario registroComentario(ComentarioRepository comentarioRepository) {
		return new RegistroComentario(comentarioRepository);
	}

	@Bean
	public DeletarComentario deletarComentario(ComentarioRepository comentarioRepository) {
		return new DeletarComentario(comentarioRepository);
	}

	@Bean
	public BuscarComentarioPorId buscarComentarioPorId(ComentarioRepository comentarioRepository) {
		return new BuscarComentarioPorId(comentarioRepository);
	}

	@Bean
	public AvaliadorDeReservas avaliadorDeReserva() {
		return new AvaliadorDeReservas();
	}

	@Bean
	public AvaliarComentario avaliadorDataComentario() {
		return new AvaliarComentario();
	}

	@Bean
	public AtualizaReserva atualizaReserva(ReservaRepository reservaRepositor) {
		return new AtualizaReserva(reservaRepositor);
	}

	@Bean
	public ListaDeReservaPorDiaHora listaReservaPorDiaHora(ReservaRepository reservaRepositor) {
		return new ListaDeReservaPorDiaHora(reservaRepositor);

	}

	@Bean
	public ListaReservaPorRestaurante listaReservaPorRestaurante(ReservaRepository reservaRepositor) {
		return new ListaReservaPorRestaurante(reservaRepositor);

	}

}

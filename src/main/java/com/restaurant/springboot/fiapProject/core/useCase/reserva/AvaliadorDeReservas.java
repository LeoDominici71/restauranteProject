package com.restaurant.springboot.fiapProject.core.useCase.reserva;

public class AvaliadorDeReservas {

	public void avaliacaoQuantidadeMesas(Integer mesas, Integer totalReservas) {

		if (mesas <= totalReservas) {

			throw new IllegalArgumentException("Não há mesas disponiveis");
		}

	}

}

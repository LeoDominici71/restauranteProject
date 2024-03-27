package com.restaurant.springboot.fiapProject.core.entities;

import java.util.Objects;

public class Comentario {

	private Long comentarioId;
	private String nomeCliente;
	private String comentario;
	private Integer avaliacao;
	private Long reservaId;

	public Comentario() {
		super();
	}

	public Comentario(Long comentarioId, String nomeCliente, String comentario, Integer avaliacao, Long reservaId) {
		super();
		this.comentarioId = comentarioId;
		this.nomeCliente = nomeCliente;
		this.comentario = comentario;
		this.avaliacao = avaliacao;
		this.reservaId = reservaId;
	}

	public Long getComentarioId() {
		return comentarioId;
	}

	public void setComentarioId(Long comentarioId) {
		this.comentarioId = comentarioId;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Integer getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Integer avaliacao) {
		this.avaliacao = avaliacao;
	}

	public Long getReservaId() {
		return reservaId;
	}

	public void setReservaId(Long reservaId) {
		this.reservaId = reservaId;
	}

	@Override
	public String toString() {
		return "Comentario [comentarioId=" + comentarioId + ", nomeCliente=" + nomeCliente + ", comentario="
				+ comentario + ", avaliacao=" + avaliacao + ", reservaId=" + reservaId + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(comentarioId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comentario other = (Comentario) obj;
		return Objects.equals(comentarioId, other.comentarioId);
	}

}
package com.example.demo.dto;

import java.time.Instant;

public class PedidoDto {

	private Long id;
	private Instant momento;

	public PedidoDto(Long id, Instant momento) {
		this.id = id;
		this.momento = momento;
	}
	public Long getId() {
		return id;
	}
	public Instant getMomento() {
		return momento;
	}
	
}

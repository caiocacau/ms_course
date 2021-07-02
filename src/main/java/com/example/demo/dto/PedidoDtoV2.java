package com.example.demo.dto;

import java.time.Instant;

import com.example.demo.entities.Usuario;
import com.example.demo.enumeration.StatusPedido;

public class PedidoDtoV2 {

	private Long id;
	private Instant momento;
//	private int statusPedido; // Caso queira retornar o código
	private StatusPedido statusPedido;
	private Usuario cliente;

	public PedidoDtoV2(Long id, Instant momento, int statusPedido, Usuario cliente) {
		this.id = id;
		this.momento = momento;
//		this.statusPedido = statusPedido; // Caso queira retornar o código
		this.statusPedido = StatusPedido.valueOf(statusPedido);
		this.cliente = cliente;
	}
	public Long getId() {
		return id;
	}
	public Instant getMomento() {
		return momento;
	}
//	public int getStatusPedido() { // Caso queira retornar o código
	public StatusPedido getStatusPedido() {
		return statusPedido;
	}
	public Usuario getCliente() {
		return cliente;
	}
	
}

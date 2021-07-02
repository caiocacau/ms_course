package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PedidoDto;
import com.example.demo.dto.PedidoDtoV2;
import com.example.demo.entities.Pedido;
import com.example.demo.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;
	
	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Pedido findById(Long id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.get();
	}

	// Outra forma com Dto
	public PedidoDto getPedidoById(Long id) {
		return repository.getPedidoById(id);
//		return new Pedido(id, dto.getMomento(), null);
	}
	
	// Outra forma com Dto
	public PedidoDtoV2 getV2PedidoById(Long id) {
		return repository.getV2PedidoById(id);
//		return new Pedido(id, dto.getMomento(), null);
	}
}

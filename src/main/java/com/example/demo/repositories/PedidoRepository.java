package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.PedidoDto;
import com.example.demo.dto.PedidoDtoV2;
import com.example.demo.entities.Pedido;

// @Repository -> é opcional pois ela ja está herdando do JpaRepository que já está registrando como um componente spring
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query(value = "Select new com.example.demo.dto.PedidoDto(p.id as id, p.momento as momento) from Pedido p where p.id = :id")
	PedidoDto getPedidoById(@Param("id") Long id);
	
	@Query(value = "Select new com.example.demo.dto.PedidoDtoV2(p.id as id, p.momento as momento, p.statusPedido as statusPedido, p.cliente as cliente) from Pedido p where p.id = :id")
	PedidoDtoV2 getV2PedidoById(@Param("id") Long id);
}

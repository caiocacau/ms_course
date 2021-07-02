package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PedidoDto;
import com.example.demo.dto.PedidoDtoV2;
import com.example.demo.entities.Pedido;
import com.example.demo.services.PedidoService;

@RestController // --> Controlador Rest
@RequestMapping(value = "/pedidos") // --> Nome para o recurso
public class PedidoResource {

	@Autowired
	private PedidoService service;

	// ResponseEntity --> Tipo de Retorno do específico do spring que retorna
	// respostas de requisições web

	@GetMapping
	public ResponseEntity<List<Pedido>> findAll() {
		List<Pedido> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	// Sem Versionamento
	@GetMapping(value = "/{id}")
	// @PathVariable -> Precisa desse comando pois senão o Spring não aceita 
	public ResponseEntity<Pedido> findById(@PathVariable Long id) { 
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj); 
	}

	// Versionamento de maneira mais simples
	@GetMapping(value = "/v1/{id}")
	// @PathVariable -> Precisa desse comando pois senão o Spring não aceita 
	public ResponseEntity<Pedido> v1FindById(@PathVariable Long id) { 
		Pedido obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	// Versionamento pelo headers(Acredito ser mais viável)
	@GetMapping(value = "/{id}", headers = "X-API-Version=v1") 
	// @PathVariable -> Precisa desse comando pois senão o Spring não aceita 
	public ResponseEntity<PedidoDto> getPedidoById(@PathVariable Long id) {
		PedidoDto obj = service.getPedidoById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Versionamento pelo headers(Acredito ser mais viável)
	@GetMapping(value = "/{id}", headers = "X-API-Version=v2")
	// @PathVariable -> Precisa desse comando pois senão o Spring não aceita 
	public ResponseEntity<PedidoDtoV2> getV2PedidoById(@PathVariable Long id) {
		PedidoDtoV2 obj = service.getV2PedidoById(id);
		return ResponseEntity.ok().body(obj);
	}

}

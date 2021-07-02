package com.example.demo.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.Categoria;
import com.example.demo.services.CategoriaService;

@RestController // --> Controlador Rest
@RequestMapping(value = "/categorias") // --> Nome para o recurso
public class CategoriaResource {

	@Autowired
	private CategoriaService service;
	
	// ResponseEntity --> Tipo de Retorno do específico do spring que retorna respostas de requisições web

	@GetMapping
	public ResponseEntity<List<Categoria>> findAll() {
		List<Categoria> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
//	@PathVariable -> Precisa desse comando pois senão o Spring não aceita
	public ResponseEntity<Categoria> findById(@PathVariable Long id) {
		Categoria obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
}

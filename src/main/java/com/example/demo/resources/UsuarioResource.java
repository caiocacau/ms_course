package com.example.demo.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.entities.Usuario;
import com.example.demo.services.UsuarioService;

@RestController // --> Controlador Rest
@RequestMapping(value = "/usuarios") // --> Nome para o recurso
public class UsuarioResource {

	@Autowired
	private UsuarioService service;
	
	// ResponseEntity --> Tipo de Retorno do específico do spring que retorna respostas de requisições web

	@GetMapping
	public ResponseEntity<List<Usuario>> findAll() {
		List<Usuario> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
//	@PathVariable -> Precisa desse comando pois senão o Spring não aceita
	public ResponseEntity<Usuario> findById(@PathVariable String id) {
		Usuario obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	// Modelo de Objeto Json para jogar Body do Postman
//	{
//		"nome": "Bob Brown",
//		"email": "bob@gmail.com",
//		"telefone": "977557755",
//		"senha": "123456"
//	}
	
	// Inserindo objeto, deve ser usado o método post
	@PostMapping
	public ResponseEntity<Usuario> insert(@RequestBody Usuario obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getCpf()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		// Para resposta sem corpo, noContent é para uma resposta vazia e o código de retorno http que não tem conteúdo é o 204
		return ResponseEntity.noContent().build();
	}
	
	// Modelo de Objeto Json para jogar Body do Postman
//	{
//	    "nome": "Bob Brown",
//	    "email": "bob@gmail,com",
//	    "telefone": "988776655"
//	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Usuario> update(@PathVariable String id, @RequestBody Usuario obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}

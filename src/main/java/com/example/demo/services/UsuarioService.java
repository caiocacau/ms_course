package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Usuario;
import com.example.demo.repositories.UsuarioRepository;
import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findById(String cpf) {
		Optional<Usuario> obj = repository.findById(cpf);
//		return obj.get(); // Sem tratamento de excecao
		// orElseThrow -> Vai tentar dar o get e se não tiver usuario vai lançar uma execão com Lambda
		return obj.orElseThrow(() -> new ResourceNotFoundException(cpf));
	}
	
	public Usuario insert(Usuario obj) {
		return repository.save(obj);
	}
	
	public void delete(String id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
//		} catch (RuntimeException e) {
//			e.printStackTrace();
		}
	}
	
	public Usuario update(String id, Usuario obj) {
		try {
			// getOne -> instancia o obj(usuario), mas, não vai no BD ainda
			// Ele só prepara o objeto monitorado pelo JPA para trabalhar como ele e em seguida no BD(mais eficiente do que o findById)
			Usuario entity = repository.getOne(id);
			updateData(entity,obj);
			return repository.save(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
//		} catch (RuntimeException e) {
//			e.printStackTrace();
//			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Usuario entity, Usuario obj) {
		entity.setNome(obj.getNome());
		entity.setEmail(obj.getEmail());
		entity.setTelefone(obj.getTelefone());
	}
}

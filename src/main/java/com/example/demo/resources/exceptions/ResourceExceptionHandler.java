package com.example.demo.resources.exceptions;

import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.demo.services.exceptions.DatabaseException;
import com.example.demo.services.exceptions.ResourceNotFoundException;

// @ControllerAdvice -> Vai interceptar as exceçoes que acontecerem 
// para que esse objeto possa executar um possivel tratamento
@ControllerAdvice 
public class ResourceExceptionHandler {

	// Essa anotation vai fazer que esse meu método vai interceptar qualquer excecao desse tipo ResourceNotFoundException
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
		// Resource = Recurso(Tradução)
		String error = "Recurso não encontrado";
		HttpStatus status = HttpStatus.NOT_FOUND;
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		// Para retornar uma resposta com um código personalizado, deve ser chamado o método .status do ResponseEntity
		return ResponseEntity.status(status).body(standardError);
	}
	
	
	// Essa anotation vai fazer que esse meu método vai interceptar qualquer excecao desse tipo DatabaseException
	@ExceptionHandler(DatabaseException.class)
	public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
		// Resource = Recurso(Tradução)
		String error = "Erro no banco de dados";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		StandardError standardError = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
		// Para retornar uma resposta com um código personalizado, deve ser chamado o método .status do ResponseEntity
		return ResponseEntity.status(status).body(standardError);
	}
}

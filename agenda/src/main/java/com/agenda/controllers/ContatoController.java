package com.agenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.entidades.Contato;
import com.agenda.services.ContatoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
	private ContatoService service;

	public ContatoController(ContatoService service) {
		this.service = service;
	}

	@GetMapping
	public ResponseEntity<?> consultar() {
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> consultar(@PathVariable("id") Long id) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(service.consultar(id));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> alterar(@PathVariable("id") Long id, @RequestBody Contato contato) {
		try {
			if (contato.getId() == null) {
				contato.setId(id);
			}
			return ResponseEntity.status(HttpStatus.OK).body(service.alterar(contato));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping
	public ResponseEntity<?> inserir(@Valid @RequestBody Contato contato) {
		try {
			Contato ct = service.getByEmail(contato.getEmail());
			if (ct != null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Contato já cadastrado com esse email");
			}
			return ResponseEntity.status(HttpStatus.OK).body(service.inserir(contato));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
}

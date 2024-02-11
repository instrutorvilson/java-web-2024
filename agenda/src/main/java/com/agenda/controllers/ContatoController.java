package com.agenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contatos")
public class ContatoController {

	@GetMapping
	public ResponseEntity<?> consultar(){
		return ResponseEntity.status(HttpStatus.OK).body("Lista de contatos");
	}
}

package com.agenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.repositories.ContatoRepository;
import com.agenda.services.ContatoService;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    private ContatoService service;
    
    public ContatoController(ContatoService service) {
    	this.service = service;
    }
	
	@GetMapping
	public ResponseEntity<?> consultar(){
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}
}

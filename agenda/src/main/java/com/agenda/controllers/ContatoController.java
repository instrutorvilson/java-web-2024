package com.agenda.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.entidades.Contato;
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
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Contato contato){
		return ResponseEntity.status(HttpStatus.OK).body(service.inserir(contato));
	}
}

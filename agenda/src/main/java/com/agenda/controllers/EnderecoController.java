package com.agenda.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agenda.entidades.Endereco;
import com.agenda.services.EnderecoService;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {
	@Autowired
	EnderecoService service;
	
	@PostMapping
	public ResponseEntity<?> inserir(@RequestBody Endereco endereco){
       return ResponseEntity.status(HttpStatus.OK).body(service.inserir(endereco));
	}
	
	@GetMapping
	public ResponseEntity<?> consultar(){
		return ResponseEntity.status(HttpStatus.OK).body(service.consultar());
	}
	
}

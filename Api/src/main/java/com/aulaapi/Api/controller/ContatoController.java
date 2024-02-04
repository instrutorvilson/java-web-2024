package com.aulaapi.Api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.aulaapi.Api.entidades.Contato;

@RestController
public class ContatoController {

	private static List<Contato> contatos = new ArrayList<>();
	
	/*@GetMapping("/contatos")
	public String ola() {
		return "hello world";
	}
	
	@GetMapping("/pessoas")
	public String tchau() {
		return "arriverdi";
	}*/
	
	@GetMapping("/contatos/{idcontato}")
	public  ResponseEntity<?> consultarUm(@PathVariable("idcontato")
	Long idcontato){
		Contato contato = null;
		for(Contato ct : contatos) {
			if(ct.getId() == idcontato) {
				contato = ct;
				break;
			}
		}
		if (contato != null) {
		   return ResponseEntity.status(HttpStatus.OK).body(contato);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("message: contato não encontrado");
	}
	
	@GetMapping("/contatos")
	public  ResponseEntity<?> consultarTodos(){
		return ResponseEntity.status(HttpStatus.OK).body(contatos);
	}
	
	@PostMapping("/contatos")
	public ResponseEntity<?> salvar(@RequestBody Contato contato) {
		contato.setId(contatos.size()+1l);
		contatos.add(contato);
		return ResponseEntity.status(HttpStatus.CREATED).body(contato);
	}

}

/* Verbos HTTP
 * Get  /contatos
 * GET /contatos/1
 * Post /contatos
 * Put  /contatos/1
 * Delete /contatos/1
 * */
 
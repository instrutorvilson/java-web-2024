package com.agenda.controllers;

import java.io.IOException;
import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.agenda.entidades.Produto;
import com.agenda.services.ImagesService;
import com.agenda.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin
public class ProdutoController {
	@Autowired
	ProdutoService service;
	
	@Autowired
	ImagesService imagesService;

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
	
	@PostMapping
	public ResponseEntity<?> inserir(
			    @RequestParam("descricao") String descricao,
	            @RequestPart("imagem") MultipartFile imagem,
	            @RequestParam("preco") BigDecimal preco)
			 {		
		
        try {
        	Produto produto = new Produto();
            produto.setDescricao(descricao);
            String urlImage = imagesService.uploadImage(imagem.getBytes());
            produto.setImagem(urlImage);
			produto.setPreco(preco);
			return ResponseEntity.status(HttpStatus.OK).body(service.inserir(produto));
		} catch (IOException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		} 
        
	    		
	}
}

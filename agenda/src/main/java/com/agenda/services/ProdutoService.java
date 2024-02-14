package com.agenda.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.entidades.Contato;
import com.agenda.entidades.Endereco;
import com.agenda.entidades.Produto;
import com.agenda.repositories.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
	private ProdutoRepository repository;
   	
	public List<Produto> consultar(){
		return repository.findAll();
	}


	public Produto inserir(Produto produto) {
		
		return repository.save(produto);
	}

	public Produto consultar(Long id) {
		Optional<Produto> opt = repository.findById(id);
		Produto prod = opt.orElseThrow(()-> new RuntimeException("Produto não existe!"));
		return prod;
	}	
}

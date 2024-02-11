package com.agenda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agenda.entidades.Contato;
import com.agenda.repositories.ContatoRepository;

@Service
public class ContatoService {
    @Autowired
	private ContatoRepository repository;
	
	public List<Contato> consultar(){
		return repository.findAll();
	}
}

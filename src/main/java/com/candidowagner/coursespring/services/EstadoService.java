package com.candidowagner.coursespring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidowagner.coursespring.domain.Estado;
import com.candidowagner.coursespring.repositories.EstadoRepository;

@Service
public class EstadoService {

	@Autowired
	private EstadoRepository repository;
	
	public List<Estado> getAll() {
		return repository.findAllByOrderByNome();
	}

}

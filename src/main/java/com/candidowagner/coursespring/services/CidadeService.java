package com.candidowagner.coursespring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.candidowagner.coursespring.domain.Cidade;
import com.candidowagner.coursespring.repositories.CidadeRepository;

@Service
public class CidadeService {

	@Autowired
	private CidadeRepository repository;
	
	public List<Cidade> findByEstado(Integer estadoId) {
		return repository.findCidades(estadoId);
	}

}

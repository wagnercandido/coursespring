package com.candidowagner.coursespring.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candidowagner.coursespring.domain.Cidade;
import com.candidowagner.coursespring.domain.Estado;
import com.candidowagner.coursespring.dto.CidadeDTO;
import com.candidowagner.coursespring.dto.EstadoDTO;
import com.candidowagner.coursespring.services.CidadeService;
import com.candidowagner.coursespring.services.EstadoService;

@RestController
@RequestMapping(value = "/estados")
public class EstadoResource {

	@Autowired
	private EstadoService service;
	
	@Autowired
	private CidadeService cidadeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> listar() {
		List<Estado> lista = service.getAll();
		List<EstadoDTO> retorno = lista.stream().map(item -> new EstadoDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(retorno);
	}
	
	@RequestMapping(value="/{id}/cidades", method = RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> getCidades(@PathVariable Integer id) {
		List<Cidade> lista = cidadeService.findByEstado(id);
		List<CidadeDTO> retorno = lista.stream().map(item -> new CidadeDTO(item)).collect(Collectors.toList());
		return ResponseEntity.ok().body(retorno);
	}

}

package com.candidowagner.coursespring.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.candidowagner.coursespring.domain.Cliente;
import com.candidowagner.coursespring.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarPorID(@PathVariable Integer id) {
		Cliente obj = service.getByID(id);
		return ResponseEntity.ok().body(obj);
	}

}

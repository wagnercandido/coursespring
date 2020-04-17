package com.candidowagner.coursespring.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.candidowagner.coursespring.domain.Categoria;
import com.candidowagner.coursespring.domain.Pedido;
import com.candidowagner.coursespring.domain.Produto;
import com.candidowagner.coursespring.repositories.CategoriaRepository;
import com.candidowagner.coursespring.repositories.ProdutoRepository;
import com.candidowagner.coursespring.services.exceptions.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;

	public Produto getByID(Integer id) {
		Optional<Produto> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	public Page<Produto> getPaginator(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy,
			String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		return repository.findDistintcByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}

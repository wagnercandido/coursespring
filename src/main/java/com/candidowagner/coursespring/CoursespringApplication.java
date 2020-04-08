package com.candidowagner.coursespring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.candidowagner.coursespring.domain.Categoria;
import com.candidowagner.coursespring.domain.Cidade;
import com.candidowagner.coursespring.domain.Cliente;
import com.candidowagner.coursespring.domain.Endereco;
import com.candidowagner.coursespring.domain.Estado;
import com.candidowagner.coursespring.domain.Produto;
import com.candidowagner.coursespring.domain.enums.TipoCliente;
import com.candidowagner.coursespring.repositories.CategoriaRepository;
import com.candidowagner.coursespring.repositories.CidadeRepository;
import com.candidowagner.coursespring.repositories.ClienteRepository;
import com.candidowagner.coursespring.repositories.EnderecoRepository;
import com.candidowagner.coursespring.repositories.EstadoRepository;
import com.candidowagner.coursespring.repositories.ProdutoRepository;

@SpringBootApplication
public class CoursespringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CoursespringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		Estado estado1 = new Estado(null, "Minas Gerais");
		Estado estado2 = new Estado(null, "São Paulo");
		
		Cidade cidade1 = new Cidade(null, "Uberlândia", estado1);
		Cidade cidade2 = new Cidade(null, "São Paulo", estado2);
		Cidade cidade3 = new Cidade(null, "Campinas", estado2);
		
		estado1.getCidades().addAll(Arrays.asList(cidade1));
		estado2.getCidades().addAll(Arrays.asList(cidade2, cidade3));
		
		estadoRepository.saveAll(Arrays.asList(estado1, estado2));
		cidadeRepository.saveAll(Arrays.asList(cidade1, cidade2, cidade3));
		
		Cliente cliente1 = new Cliente(null, "Maria Silva", "maria@email.com", "36378912377", TipoCliente.PESSOA_FISICA);

		cliente1.getTelefones().addAll(Arrays.asList("11988976431", "11998746532"));
		
		Endereco endereco1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardim Botânico", "58021456", cliente1, cidade1);
		Endereco endereco2 = new Endereco(null, "Avenida Matos", "105", "Sala 100", "Centro", "58028526", cliente1, cidade2);
		
		cliente1.getEnderecos().addAll(Arrays.asList(endereco1, endereco2));
		
		clienteRepository.saveAll(Arrays.asList(cliente1));
		enderecoRepository.saveAll(Arrays.asList(endereco1, endereco2));
		
	}

}

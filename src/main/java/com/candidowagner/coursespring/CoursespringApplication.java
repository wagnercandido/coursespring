package com.candidowagner.coursespring;

import java.text.SimpleDateFormat;
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
import com.candidowagner.coursespring.domain.ItemPedido;
import com.candidowagner.coursespring.domain.Pagamento;
import com.candidowagner.coursespring.domain.PagamentoBoleto;
import com.candidowagner.coursespring.domain.PagamentoCartao;
import com.candidowagner.coursespring.domain.Pedido;
import com.candidowagner.coursespring.domain.Produto;
import com.candidowagner.coursespring.domain.enums.EstadoPagamento;
import com.candidowagner.coursespring.domain.enums.TipoCliente;
import com.candidowagner.coursespring.repositories.CategoriaRepository;
import com.candidowagner.coursespring.repositories.CidadeRepository;
import com.candidowagner.coursespring.repositories.ClienteRepository;
import com.candidowagner.coursespring.repositories.EnderecoRepository;
import com.candidowagner.coursespring.repositories.EstadoRepository;
import com.candidowagner.coursespring.repositories.ItemPedidoRepository;
import com.candidowagner.coursespring.repositories.PagamentoRepository;
import com.candidowagner.coursespring.repositories.PedidoRepository;
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
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CoursespringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		Categoria cat3 = new Categoria(null, "Cama mesa e balho");
		Categoria cat4 = new Categoria(null, "Eletrônicos");
		Categoria cat5 = new Categoria(null, "Jardinagem");
		Categoria cat6 = new Categoria(null, "Decoração");
		Categoria cat7 = new Categoria(null, "Perfumaria");
		Categoria cat8 = new Categoria(null, "Saúde");
		Categoria cat9 = new Categoria(null, "Pra sua casa");
		Categoria cat10 = new Categoria(null, "Pra você");
		Categoria cat11 = new Categoria(null, "Viagens");
		Categoria cat12 = new Categoria(null, "Teste 1");
		Categoria cat13 = new Categoria(null, "Teste 2");

		Produto prod1 = new Produto(null, "Computador", 2000.00);
		Produto prod2 = new Produto(null, "Impressora", 800.00);
		Produto prod3 = new Produto(null, "Mouse", 80.00);
		Produto prod4 = new Produto(null, "Mesa de escritório", 80.00);
		Produto prod5 = new Produto(null, "Toalha", 300.00);
		Produto prod6 = new Produto(null, "Colcha", 50.00);
		Produto prod7 = new Produto(null, "TV true color", 200.00);
		Produto prod8 = new Produto(null, "Roçadeira", 800.00);
		Produto prod9 = new Produto(null, "Abajour", 100.00);
		Produto prod10 = new Produto(null, "Pendente", 180.00);
		Produto prod11 = new Produto(null, "Sampoo", 90.00);

		cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));

		prod1.getCategorias().addAll(Arrays.asList(cat1));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategorias().addAll(Arrays.asList(cat1));
		
		cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));
		cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));
		cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
		cat5.getProdutos().addAll(Arrays.asList(prod8));
		cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
		cat7.getProdutos().addAll(Arrays.asList(prod11));

		prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
		prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
		prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
		prod4.getCategorias().addAll(Arrays.asList(cat2));
		prod5.getCategorias().addAll(Arrays.asList(cat3));
		prod6.getCategorias().addAll(Arrays.asList(cat3));
		prod7.getCategorias().addAll(Arrays.asList(cat4));
		prod8.getCategorias().addAll(Arrays.asList(cat5));
		prod9.getCategorias().addAll(Arrays.asList(cat6));
		prod10.getCategorias().addAll(Arrays.asList(cat6));
		prod11.getCategorias().addAll(Arrays.asList(cat7));

		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7, cat8, cat9, cat10, cat11, cat12, cat13));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5,prod6, prod7, prod8, prod9, prod10, prod11));
		
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy HH:mm");
		
		Pedido pedido1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente1, endereco1);
		Pedido pedido2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cliente1, endereco2);
		
		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, pedido1, 6);
		pedido1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, pedido2, sdf.parse("20/10/2017 00:00"), null);
		pedido2.setPagamento(pagto2);
		
		cliente1.getPedidos().addAll(Arrays.asList(pedido1, pedido2));

		pedidoRepository.saveAll(Arrays.asList(pedido1, pedido2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido item1 = new ItemPedido(pedido1, prod1, 0.00, 1, 2000.00);
		ItemPedido item2 = new ItemPedido(pedido1, prod3, 0.00, 2, 80.00);
		ItemPedido item3 = new ItemPedido(pedido2, prod2, 100.00, 1, 800.00);
		
		pedido1.getItens().addAll(Arrays.asList(item1, item2));
		pedido2.getItens().addAll(Arrays.asList(item3));
		
		prod1.getItens().addAll(Arrays.asList(item1));
		prod2.getItens().addAll(Arrays.asList(item3));
		prod3.getItens().addAll(Arrays.asList(item2));
		
		itemPedidoRepository.saveAll(Arrays.asList(item1, item2, item3));
		
	}

}

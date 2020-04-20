package com.candidowagner.coursespring.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.candidowagner.coursespring.domain.ItemPedido;
import com.candidowagner.coursespring.domain.PagamentoBoleto;
import com.candidowagner.coursespring.domain.Pedido;
import com.candidowagner.coursespring.domain.enums.EstadoPagamento;
import com.candidowagner.coursespring.repositories.ItemPedidoRepository;
import com.candidowagner.coursespring.repositories.PagamentoRepository;
import com.candidowagner.coursespring.repositories.PedidoRepository;
import com.candidowagner.coursespring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EmailService emailService;

	public Pedido getByID(Integer id) {
		Optional<Pedido> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido save(Pedido obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.setCliente(clienteService.getByID(obj.getCliente().getId()));
		obj.getPagamento().setEstado(EstadoPagamento.PENDENTE);
		obj.getPagamento().setPedido(obj);
		if (obj.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamento = (PagamentoBoleto) obj.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagamento, obj.getInstante());
		}
		obj = repository.save(obj);
		pagamentoRepository.save(obj.getPagamento());

		for (ItemPedido item : obj.getItens()) {
			item.setDesconto(0.0);
			item.setProduto(produtoService.getByID(item.getProduto().getId()));
			item.setPreco(item.getProduto().getPreco());
			item.setPedido(obj);
		}

		itemPedidoRepository.saveAll(obj.getItens());
		emailService.enviarConfirmacaoPedidoHtmlEmail(obj);
		return obj;
	}

}

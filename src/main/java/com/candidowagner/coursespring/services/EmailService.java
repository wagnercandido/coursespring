package com.candidowagner.coursespring.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.candidowagner.coursespring.domain.Pedido;

public interface EmailService {
	
	void enviarConfirmacaoPedido(Pedido obj);
	
	void enviarEmail(SimpleMailMessage msg);
	
	void enviarConfirmacaoPedidoHtmlEmail(Pedido obj);
	
	void enviarHtmlEmail(MimeMessage msg);

}

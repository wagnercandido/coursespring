package com.candidowagner.coursespring.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.candidowagner.coursespring.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default-sender}")
	private String sender;
	
	@Override
	public void enviarConfirmacaoPedido(Pedido obj) {
		SimpleMailMessage simpleMail = prepareSimpleMailMessageFromPedido(obj);
		enviarEmail(simpleMail);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido obj) {
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setTo(obj.getCliente().getEmail());
		simpleMail.setFrom(sender);
		simpleMail.setSubject("Pedido confirmado! Código: " + obj.getId());
		simpleMail.setSentDate(new Date(System.currentTimeMillis()));
		simpleMail.setText(obj.toString());
		return simpleMail;
	}
	

}

package com.candidowagner.coursespring.services;

import org.springframework.mail.SimpleMailMessage;

import com.candidowagner.coursespring.domain.Pedido;

public interface EmailService {
	
	void enviarConfirmacaoPedido(Pedido obj);
	
	void enviarEmail(SimpleMailMessage msg);

}

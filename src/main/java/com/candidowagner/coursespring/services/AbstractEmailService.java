package com.candidowagner.coursespring.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.candidowagner.coursespring.domain.Cliente;
import com.candidowagner.coursespring.domain.Pedido;

public abstract class AbstractEmailService implements EmailService {
	
	@Value("${default-sender}")
	private String sender;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
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
	
	protected String htmlFromTemplatePedido(Pedido obj) {
		Context context = new Context();
		context.setVariable("pedido", obj);
		return templateEngine.process("email/confirmacaoPedido", context);
	}

	@Override
	public void enviarConfirmacaoPedidoHtmlEmail(Pedido obj) {
		MimeMessage mimeMessage;
		try {
			mimeMessage = prepareMimeMessageFromPedido(obj);
			enviarHtmlEmail(mimeMessage);
		} catch (MessagingException e) {
			enviarConfirmacaoPedido(obj);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
		mimeMessageHelper.setTo(obj.getCliente().getEmail());
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setSubject("Pedido Confirmado! Código: " + obj.getId());
		mimeMessageHelper.setSentDate(new Date(System.currentTimeMillis()));
		mimeMessageHelper.setText(htmlFromTemplatePedido(obj), true);
		return mimeMessage;
	}
	
	@Override
	public void enviarEmailNovaSenha(Cliente cliente, String novaSenha) {
		SimpleMailMessage simpleMail = prepareEmailNovaSenha(cliente, novaSenha);
		enviarEmail(simpleMail);	
	}

	protected SimpleMailMessage prepareEmailNovaSenha(Cliente cliente, String novaSenha) {
		SimpleMailMessage simpleMail = new SimpleMailMessage();
		simpleMail.setTo(cliente.getEmail());
		simpleMail.setFrom(sender);
		simpleMail.setSubject("Solicitação de nova senha");
		simpleMail.setSentDate(new Date(System.currentTimeMillis()));
		simpleMail.setText("Nova senha: " + novaSenha);
		return simpleMail;
	}

}

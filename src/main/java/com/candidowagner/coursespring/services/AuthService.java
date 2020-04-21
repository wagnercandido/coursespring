package com.candidowagner.coursespring.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.candidowagner.coursespring.domain.Cliente;
import com.candidowagner.coursespring.repositories.ClienteRepository;
import com.sun.jdi.ObjectCollectedException;

@Service
public class AuthService {

	@Autowired
	private BCryptPasswordEncoder encodePassword;

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private EmailService emailService;

	private Random random = new Random();

	public void sendNewPassword(String email) {
		
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectCollectedException("E-mail não encontrado");
		}
		
		String novaSenha = novaSenha();
		cliente.setSenha(encodePassword.encode(novaSenha));
		
		clienteRepository.save(cliente);
		emailService.enviarEmailNovaSenha(cliente, novaSenha);
		
	}

	private String novaSenha() {
		char[] vetor = new char[10];
		for (int i = 0; i<10; i++) {
			vetor[i] = randomChar();
		}
		return new String(vetor);
	}

	private char randomChar() {
		int option = random.nextInt(3);
		if (option == 0) {
			return (char) (random.nextInt(10) + 48);
		} else if (option == 1) {
			return (char) (random.nextInt(26) + 65);
		} else {
			return (char) (random.nextInt(26) + 97);
		}
	}

}

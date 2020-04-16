package com.candidowagner.coursespring.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.candidowagner.coursespring.domain.Cliente;
import com.candidowagner.coursespring.domain.enums.TipoCliente;
import com.candidowagner.coursespring.dto.ClienteFullDTO;
import com.candidowagner.coursespring.repositories.ClienteRepository;
import com.candidowagner.coursespring.resources.exception.FieldMessage;
import com.candidowagner.coursespring.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteFullDTO> {
	
	@Autowired
	private ClienteRepository repository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteFullDTO objDto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();
		
		// inclua os testes aqui, inserindo erros na lista
		
		if(objDto.getTipo().equals(TipoCliente.PESSOA_FISICA.getCod()) && !BR.isValidCPF(objDto.getDocumento())) {
			list.add(new FieldMessage("documento", "CPF Inválido"));
		}
		if(objDto.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getDocumento())) {
			list.add(new FieldMessage("documento", "CNPJ Inválido"));
		}
		
		Cliente objAux = repository.findByEmail(objDto.getEmail());
		if(objAux != null) {
			list.add( new FieldMessage("email", "Email já existente no sistema"));
		}
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
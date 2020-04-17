package com.candidowagner.coursespring.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.candidowagner.coursespring.domain.PagamentoBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoBoleto pagamento, Date data) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(calendar.getTime());
	}
	
	

}

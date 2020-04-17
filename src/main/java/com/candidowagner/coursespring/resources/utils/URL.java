package com.candidowagner.coursespring.resources.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class URL {
	
	public static List<Integer> decodeNumberList(String text) {
		return Arrays.asList(text.split(",")).stream().map(numero -> Integer.parseInt(numero)).collect(Collectors.toList());

		// TODO o método comentado faz a mesma coisa que o retorno acima.
		
//		List<Integer> lista = new ArrayList<>();
//		String[] vetor = text.split(",");
//		for (int i = 0; i < vetor.length; i++) {
//			lista.add(Integer.parseInt(vetor[i]));
//		}
//		return lista;
	}
	
	public static String decodeString(String text) {
		try {
			return URLDecoder.decode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
	}

}

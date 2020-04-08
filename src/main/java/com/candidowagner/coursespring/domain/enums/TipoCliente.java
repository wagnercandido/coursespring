package com.candidowagner.coursespring.domain.enums;

import org.dom4j.IllegalAddException;

public enum TipoCliente {

	PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

	private int cod;
	private String descricao;

	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	public int getCod() {
		return cod;
	}

	public String descricao() {
		return this.descricao;
	}

	public static TipoCliente toEnum(Integer cod) {
		if (cod == null) {
			return null;
		}

		for (TipoCliente tipo : TipoCliente.values()) {
			if (cod.equals(tipo.getCod())) {
				return tipo;
			}
		}

		throw new IllegalAddException("Enum Inválido " + cod);

	}

}

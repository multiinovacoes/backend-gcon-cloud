package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {
	
	ATIVO(0,"Ativo"),
	IANTIVO(1, "Inativo");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, StatusEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (StatusEnum statusEnum : StatusEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	StatusEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

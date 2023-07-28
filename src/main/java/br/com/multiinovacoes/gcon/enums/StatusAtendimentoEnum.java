package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusAtendimentoEnum {
	
	STATUS_ANDAMENTO(0,"Em andamento"),
	STATUS_RESOLVIDO(1, "Resolvido"),
	STATUS_CANCELADO(2, "Cancelado");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, StatusAtendimentoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (StatusAtendimentoEnum statusEnum : StatusAtendimentoEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	StatusAtendimentoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusAtendimentoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

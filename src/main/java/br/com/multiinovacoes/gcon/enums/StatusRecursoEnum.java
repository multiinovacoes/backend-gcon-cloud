package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusRecursoEnum {
	
	RECURSO_ANDAMENTO(1,"Em andamento"),
	RECURSO_RESOLVIDO(2,"Resolvido"),
	RECURSO_CANCELADO(3,"Cancelado");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, StatusRecursoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (StatusRecursoEnum statusEnum : StatusRecursoEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	StatusRecursoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static StatusRecursoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

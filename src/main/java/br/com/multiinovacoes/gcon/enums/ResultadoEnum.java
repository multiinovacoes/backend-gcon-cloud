package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum ResultadoEnum {
	
	PROCEDENTE(1,"Procedente"),
	PROCEDENTE_PARCIAL(2,"`Procedente parcial"),
	IMPROCEDENTE(3,"Improcedente");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, ResultadoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (ResultadoEnum statusEnum : ResultadoEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	ResultadoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static ResultadoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum PriorizacaoEnum {
	
	ALTA(1,"Alta"),
	MEDIA(2, "Média"),
	BAIXA(3, "Baixa");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, PriorizacaoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (PriorizacaoEnum priorizacaoEnum : PriorizacaoEnum.values()) {
			funcaoPegaDescricao.put(priorizacaoEnum.getCodigo(), priorizacaoEnum);
		}
	}
	
	PriorizacaoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static PriorizacaoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

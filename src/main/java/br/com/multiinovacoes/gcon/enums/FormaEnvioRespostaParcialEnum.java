package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum FormaEnvioRespostaParcialEnum {
	
	NAO_INFORMADO(0,"Não Informado"),
	CARTA(1,"Carta"),
	EMAIL(2, "Email"),
	TELEFONE(4, "Telefone"),
	PRESENCIAL(5, "Presencial");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, FormaEnvioRespostaParcialEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (FormaEnvioRespostaParcialEnum formaEnvioResposta : FormaEnvioRespostaParcialEnum.values()) {
			funcaoPegaDescricao.put(formaEnvioResposta.getCodigo(), formaEnvioResposta);
		}
	}
	
	FormaEnvioRespostaParcialEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static FormaEnvioRespostaParcialEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

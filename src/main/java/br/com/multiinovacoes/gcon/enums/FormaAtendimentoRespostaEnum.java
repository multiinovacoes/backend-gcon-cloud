package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum FormaAtendimentoRespostaEnum {
	
	NAO_INFORMADO(0,"Não Informado"),
	EMAIL(1, "Email"),
	CARTA(2, "Carta"),
	TELEFONE(3, "Telefone"),
	PRESENCIAL(4, "Presencial"),
	OUTROS(5, "Outros"),
	O800(6, "0800");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, FormaAtendimentoRespostaEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (FormaAtendimentoRespostaEnum formaEnvioResposta : FormaAtendimentoRespostaEnum.values()) {
			funcaoPegaDescricao.put(formaEnvioResposta.getCodigo(), formaEnvioResposta);
		}
	}
	
	FormaAtendimentoRespostaEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static FormaAtendimentoRespostaEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

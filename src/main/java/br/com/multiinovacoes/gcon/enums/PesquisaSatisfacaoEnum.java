package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum PesquisaSatisfacaoEnum {
	
	SIM(1,"Sim"),
	NAO(0, "Não");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, PesquisaSatisfacaoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (PesquisaSatisfacaoEnum pesquisaSatisfacaoEnum : PesquisaSatisfacaoEnum.values()) {
			funcaoPegaDescricao.put(pesquisaSatisfacaoEnum.getCodigo(), pesquisaSatisfacaoEnum);
		}
	}
	
	PesquisaSatisfacaoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static PesquisaSatisfacaoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum ResponsavelAnexoEnum {
	
	CIDADAO(0,"Cidadão"),
	SAC(1, "SAC"),
	AUTORIDADE_MONITORAMENTO(2, "Autoridade de Monitoramento"),
	RECURSO_1_INSTANCIA(3, "Recurso 1ª Instância"),
	RECURSO_2_INSTANCIA(4, "Recurso 2ª Instância");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, ResponsavelAnexoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (ResponsavelAnexoEnum statusEnum : ResponsavelAnexoEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	ResponsavelAnexoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static ResponsavelAnexoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

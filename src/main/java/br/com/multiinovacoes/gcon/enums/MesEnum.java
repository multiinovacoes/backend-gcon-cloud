package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum MesEnum {
	
	JANEIRO(1,"JANEIRO"),
	FEVEREIRO(2, "FEVEREIRO"),
	MARCO(3, "MARÇO"),
	ABRIL(4, "ABRIL"),
	MAIO(5, "MAIO"),
	JUNHO(6, "JUNHO"),
	JULHO(7, "JULHO"),
	AGOSTO(8, "AGOSTO"),
	SETEMBRO(9, "SETEMBRO"),
	OUTUBRO(10, "OUTUBRO"),
	NOVEMBRO(11, "NOVEMBRO"),
	DEZEMBRO(12, "DEZEMBRO");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, MesEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (MesEnum statusEnum : MesEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	MesEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static MesEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

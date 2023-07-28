package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoRecursoEnum {
	
	RECURSO_1_INSTANCIA(1,"1ª Instância"),
	RECURSO_2_INSTANCIA(2,"2ª Instância");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, TipoRecursoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (TipoRecursoEnum statusEnum : TipoRecursoEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	TipoRecursoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoRecursoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

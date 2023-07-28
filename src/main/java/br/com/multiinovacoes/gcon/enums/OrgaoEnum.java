package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrgaoEnum {
	
	CECMG(1l,"Ouvidoria do Centro Educacional Católica do leste de Minas Gerais"),
	UBEC(2l, "Ouvidoria da UBEC"),
	CPM(3l, "Ouvidoria do Colégio Padre de Man"),
	CECB(4l, "Ouvidoria do Centro Educacional Católica de Brasília"),
	UCB(5l, "Ouvidoria da UCB"),
	UNILESTE(6l, "Ouvidoria da UNILESTE"),
	//FICR(7l, "Ouvidoria da FICR"),
	UNICATOLICA(8l, "Ouvidoria da UNICATOLICA"),
	//CECC(9l, "Ouvidoria do Colégio Católica de Curitiba"),
	//CECMA(10l, "Ouvidoria da Machado de Assis");

	SESC(9l,"Ouvidoria do SESC Recife"),
	SENAI(7l, "Ouvidoria do Sistema FIEPE"),
	HOSPITAL_BALBINO(10l, "Ouvidoria do Hospital Balbino"),
	PREFEITURA_BARREIROS(11l, "Ouvidoria da Prefeitura do Barreiros");
	
	private Long codigo;
	
	private String descricao;
	
	private static final Map<Long, OrgaoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (OrgaoEnum orgaoEnum : OrgaoEnum.values()) {
			funcaoPegaDescricao.put(orgaoEnum.getCodigo(), orgaoEnum);
		}
	}
	
	OrgaoEnum(Long codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static OrgaoEnum pegarDescricao(Long codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

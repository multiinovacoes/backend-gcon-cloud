package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum OrigemManifestacaoEnum {
	
	ATENDIMENTO_TELEFONICO(1,"Atendimento Telefônico"),
	EMAIL(2, "Email"),
	SITE(3, "Site"),
	ATENDIMENTO_PESSOAL(4, "Atendimento Pessoal"),
	PORTAL(5, "Portal"),
	OFICIO(6, "Ofício"),
	MATERIA_JORNAL(7, "Matéria de Jornal"),
	CAIXA_SUGESTAO(8, "Caixa de Sugestão"),
	CARTA(9, "Carta"),
	FAX(10, "Fax"),
	O800(11, "0800"),
	AUTO_ATENDIMENTO(12, "Auto-Atendimento"),
	MOBILE(13, "Mobile"),
	DISQUE_DENUNCIA(14, "Disque-Denúncia"),
	MEDIACAO_CONFLITO(15, "Mediação de Conflitos");
	
	private Integer codigo;
	
	private String descricao;
	
	private static final Map<Integer, OrigemManifestacaoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (OrigemManifestacaoEnum formaEnvioResposta : OrigemManifestacaoEnum.values()) {
			funcaoPegaDescricao.put(formaEnvioResposta.getCodigo(), formaEnvioResposta);
		}
	}
	
	OrigemManifestacaoEnum(Integer codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static OrigemManifestacaoEnum pegarDescricao(Integer codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

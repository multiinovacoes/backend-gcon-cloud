package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoModeloDocumentoEnum {
	
	MODELO_DOCUMENTO_ENCAMINHAMENTO(1l,"Modelo de Encaminhamento"),
	MODELO_DOCUMENTO_DESPACHO(2l,"Modelo de Despacho"),
	MODELO_DOCUMENTO_RESPOSTA(3l, "Modelo de Resposta Parcial"),
	MODELO_DOCUMENTO_PRORROGACAO_PRAZO(4l, "Modelo de Prorrogação de Prazo"),
	MODELO_DOCUMENTO_RESPOSTA_SETOR(5l, "Modelo de Resposta do Setor"),
	MODELO_DOCUMENTO_CONCLUSAO_FINAL(6l, "Modelo de Conclusão Final");
	
	
	private Long codigo;
	
	private String descricao;
	
	private static final Map<Long, TipoModeloDocumentoEnum> funcaoPegaDescricao = new HashMap<>();
	
	static {
		for (TipoModeloDocumentoEnum statusEnum : TipoModeloDocumentoEnum.values()) {
			funcaoPegaDescricao.put(statusEnum.getCodigo(), statusEnum);
		}
	}
	
	TipoModeloDocumentoEnum(Long codigo, String descricao){
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Long getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static TipoModeloDocumentoEnum pegarDescricao(Long codigo) {
		return funcaoPegaDescricao.get(codigo);
	}
	

}

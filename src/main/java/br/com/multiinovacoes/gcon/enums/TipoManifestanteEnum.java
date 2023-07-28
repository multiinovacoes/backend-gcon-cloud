package br.com.multiinovacoes.gcon.enums;

import java.util.HashMap;
import java.util.Map;

public enum TipoManifestanteEnum {
	
	ANONIMO_ORGAO_1(1l,1l),
	ANONIMO_ORGAO_2(2l,7l),
	ANONIMO_ORGAO_3(3l,15l),
	ANONIMO_ORGAO_4(4l,21l),
	ANONIMO_ORGAO_5(5l,27l),
	ANONIMO_ORGAO_6(6l,37l),
	ANONIMO_ORGAO_7(7l,45l),
	ANONIMO_ORGAO_8(8l,49l),
	ANONIMO_ORGAO_9(9l,55l),
	ANONIMO_ORGAO_10(10l,57l);
	
	private Long codigo;
	
	private Long orgao;
	
	private static final Map<Long, TipoManifestanteEnum> funcaoPegaCodigo = new HashMap<>();
	
	static {
		for (TipoManifestanteEnum tipoManifestanteEnum : TipoManifestanteEnum.values()) {
			funcaoPegaCodigo.put(tipoManifestanteEnum.getOrgao(), tipoManifestanteEnum);
		}
	}
	
	TipoManifestanteEnum(Long orgao, Long codigo){
		this.orgao = orgao;
		this.codigo = codigo;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public Long getOrgao() {
		return orgao;
	}

	public static TipoManifestanteEnum pegarCodigoAnonimoOrgao(Long orgao) {
		return funcaoPegaCodigo.get(orgao);
	}
	

}

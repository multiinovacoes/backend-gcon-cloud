package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.TipoManifestanteDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Tipo Manifestante Response")
public class TipoManifestanteResponse {
	
	@ApiModelProperty(value = "Tipo Manifestante Dto")
	private TipoManifestanteDto tipoManifestanteDto;
	
	public TipoManifestanteResponse(TipoManifestanteDto tipoManifestanteDto) {
		this.tipoManifestanteDto = tipoManifestanteDto;
	}

	public TipoManifestanteDto getTipoManifestanteDto() {
		return tipoManifestanteDto;
	}

	public void setTipoManifestanteDto(TipoManifestanteDto tipoManifestanteDto) {
		this.tipoManifestanteDto = tipoManifestanteDto;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	


}

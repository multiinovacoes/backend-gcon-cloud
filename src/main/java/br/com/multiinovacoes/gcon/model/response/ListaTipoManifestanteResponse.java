package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.TipoManifestanteDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Tipo Manifestante Response")
public class ListaTipoManifestanteResponse {
	
	@ApiModelProperty(value = "Lista de Tipos Manifestante")
	private List<TipoManifestanteDto> tipoManifestanteDtoList;
	
	public ListaTipoManifestanteResponse(List<TipoManifestanteDto> tipoManifestanteDtoList) {
		this.tipoManifestanteDtoList = tipoManifestanteDtoList;
	}


	public List<TipoManifestanteDto> getTipoManifestanteDtoList() {
		return tipoManifestanteDtoList;
	}


	public void setTipoManifestanteDtoList(List<TipoManifestanteDto> tipoManifestanteDtoList) {
		this.tipoManifestanteDtoList = tipoManifestanteDtoList;
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

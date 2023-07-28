package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.HistoricoUsuarioDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Historico Response")
public class ListaHistoricoUsuarioResponse {
	
	@ApiModelProperty(value = "Lista de Historico de Usuario")
	private List<HistoricoUsuarioDto> historicoManifestanteDtoList;

	public ListaHistoricoUsuarioResponse(List<HistoricoUsuarioDto> historicoManifestanteDtoList) {
		super();
		this.historicoManifestanteDtoList = historicoManifestanteDtoList;
	}


	public List<HistoricoUsuarioDto> getHistoricoManifestanteDtoList() {
		return historicoManifestanteDtoList;
	}


	public void setHistoricoManifestanteDtoList(List<HistoricoUsuarioDto> historicoManifestanteDtoList) {
		this.historicoManifestanteDtoList = historicoManifestanteDtoList;
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

package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.OrigemManifestacaoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Origem Manifestação Response")
public class ListaOrigemManifestacaoResponse {
	
	@ApiModelProperty(value = "Lista de Origem Manifestação")
	private List<OrigemManifestacaoDto> origemManifestacaoDtoList;
	
	public ListaOrigemManifestacaoResponse(List<OrigemManifestacaoDto> origemManifestacaoDtoList) {
		this.origemManifestacaoDtoList = origemManifestacaoDtoList;
	}

	public List<OrigemManifestacaoDto> getOrigemManifestacaoDtoList() {
		return origemManifestacaoDtoList;
	}

	public void setOrigemManifestacaoDtoList(List<OrigemManifestacaoDto> origemManifestacaoDtoList) {
		this.origemManifestacaoDtoList = origemManifestacaoDtoList;
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

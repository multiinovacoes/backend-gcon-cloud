package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.OrigemManifestacaoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Origem Manifestação Response")
public class OrigemManifestacaoResponse {

	@ApiModelProperty(value = "Origem Manifestação Dto")
	private OrigemManifestacaoDto origemManifestacaoDto;
	
	public OrigemManifestacaoResponse(OrigemManifestacaoDto origemManifestacaoDto) {
		this.origemManifestacaoDto = origemManifestacaoDto;
	}

	public OrigemManifestacaoDto getOrigemManifestacaoDto() {
		return origemManifestacaoDto;
	}

	public void setOrigemManifestacaoDto(OrigemManifestacaoDto origemManifestacaoDto) {
		this.origemManifestacaoDto = origemManifestacaoDto;
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

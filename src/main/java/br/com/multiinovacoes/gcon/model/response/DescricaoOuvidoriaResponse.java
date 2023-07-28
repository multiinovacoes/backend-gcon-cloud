package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.DescricaoOuvidoriaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Descrição Ouvidoria Response")
public class DescricaoOuvidoriaResponse {
	
	@ApiModelProperty(value = "Descrição Ouvidoria Dto")
	private DescricaoOuvidoriaDto descricaoOuvidoriaDto;
	
	public DescricaoOuvidoriaResponse(DescricaoOuvidoriaDto descricaoOuvidoriaDto) {
		this.descricaoOuvidoriaDto = descricaoOuvidoriaDto;
	}

	public DescricaoOuvidoriaDto getDescricaoOuvidoriaDto() {
		return descricaoOuvidoriaDto;
	}

	public void setDescricaoOuvidoriaDto(DescricaoOuvidoriaDto descricaoOuvidoriaDto) {
		this.descricaoOuvidoriaDto = descricaoOuvidoriaDto;
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.DescricaoEmailDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Descrição Email Response")
public class DescricaoEmailResponse {
	
	@ApiModelProperty(value = "Descrição Email Dto")
	private DescricaoEmailDto descricaoEmailDto;
	
	public DescricaoEmailResponse(DescricaoEmailDto descricaoEmailDto) {
		this.descricaoEmailDto = descricaoEmailDto;
	}

	public DescricaoEmailDto getDescricaoEmailDto() {
		return descricaoEmailDto;
	}

	public void setDescricaoEmailDto(DescricaoEmailDto descricaoEmailDto) {
		this.descricaoEmailDto = descricaoEmailDto;
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

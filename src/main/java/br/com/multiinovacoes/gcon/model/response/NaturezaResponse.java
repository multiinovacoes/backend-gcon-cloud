package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.NaturezaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Natureza Response")
public class NaturezaResponse {
	
    @ApiModelProperty(value = "Natureza Dto")
	NaturezaDto naturezaDto;
	
	public NaturezaResponse(NaturezaDto naturezaDto) {
		this.naturezaDto = naturezaDto;
	}
	
	public NaturezaDto getNaturezaDto() {
		return naturezaDto;
	}

	public void setNaturezaDto(NaturezaDto naturezaDto) {
		this.naturezaDto = naturezaDto;
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

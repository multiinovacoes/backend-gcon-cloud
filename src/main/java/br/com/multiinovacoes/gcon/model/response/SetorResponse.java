package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.SetorDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Setor Response")
public class SetorResponse {
	
	@ApiModelProperty(value = "Setor Dto")
	private SetorDto setorDto;
	
	public SetorResponse(SetorDto setorDto) {
		this.setorDto = setorDto;
	}

	public SetorDto getSetorDto() {
		return setorDto;
	}

	public void setSetorDto(SetorDto setorDto) {
		this.setorDto = setorDto;
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

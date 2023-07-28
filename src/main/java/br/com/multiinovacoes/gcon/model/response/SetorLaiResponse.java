package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.SetorLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Setor Lai Response")
public class SetorLaiResponse {
	
	@ApiModelProperty(value = "Setor Lai Dto")
	private SetorLaiDto setorLaiDto;
	
	public SetorLaiResponse(SetorLaiDto setorLaiDto) {
		this.setorLaiDto = setorLaiDto;
	}

	public SetorLaiDto getSetorLaiDto() {
		return setorLaiDto;
	}

	public void setSetorLaiDto(SetorLaiDto setorLaiDto) {
		this.setorLaiDto = setorLaiDto;
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.AreaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Area Response")
public class AreaResponse {
	
	@ApiModelProperty(value = "Área Dto")
	private AreaDto areaDto;
	
	public AreaResponse(AreaDto areaDto) {
		this.areaDto = areaDto;
	}


	public AreaDto getAreaDto() {
		return areaDto;
	}


	public void setAreaDto(AreaDto areaDto) {
		this.areaDto = areaDto;
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

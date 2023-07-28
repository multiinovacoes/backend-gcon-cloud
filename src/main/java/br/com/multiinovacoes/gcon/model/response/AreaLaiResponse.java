package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.AreaLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Area Lai Response")
public class AreaLaiResponse {
	
	@ApiModelProperty(value = "Área Dto")
	private AreaLaiDto areaDto;
	
	public AreaLaiResponse(AreaLaiDto areaDto) {
		this.areaDto = areaDto;
	}


	public AreaLaiDto getAreaDto() {
		return areaDto;
	}


	public void setAreaDto(AreaLaiDto areaDto) {
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.AssuntoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Assunto Response")
public class AssuntoResponse {
	
	@ApiModelProperty(value = "Assunto Dto")
	private AssuntoDto assuntoDto;
	
	public AssuntoResponse(AssuntoDto assuntoDto) {
		this.assuntoDto = assuntoDto;
	}

	public AssuntoDto getAssuntoDto() {
		return assuntoDto;
	}

	public void setAssuntoDto(AssuntoDto assuntoDto) {
		this.assuntoDto = assuntoDto;
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.AssuntoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Assunto Lai Response")
public class AssuntoLaiResponse {
	
	@ApiModelProperty(value = "Assunto Dto")
	private AssuntoLaiDto assuntoDto;
	
	public AssuntoLaiResponse(AssuntoLaiDto assuntoDto) {
		this.assuntoDto = assuntoDto;
	}


	public AssuntoLaiDto getAssuntoDto() {
		return assuntoDto;
	}


	public void setAssuntoDto(AssuntoLaiDto assuntoDto) {
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

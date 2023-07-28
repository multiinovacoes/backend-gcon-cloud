package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.RecursoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Recurso Lai Response")
public class RecursoLaiResponse {

	
	@ApiModelProperty(value = "Recurso Lai Dto")
	private RecursoLaiDto recursoLaiDto;
	
	public RecursoLaiResponse(RecursoLaiDto recursoLaiDto) {
		this.recursoLaiDto = recursoLaiDto;
	}

	public RecursoLaiDto getRecursoLaiDto() {
		return recursoLaiDto;
	}

	public void setRecursoLaiDto(RecursoLaiDto recursoLaiDto) {
		this.recursoLaiDto = recursoLaiDto;
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

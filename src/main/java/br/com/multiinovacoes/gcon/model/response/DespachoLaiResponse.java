package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.DespachoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Despacho Lai Response")
public class DespachoLaiResponse {
	
	@ApiModelProperty(value = "Despacho Lai Dto")
	private DespachoLaiDto despachoLaiDto;
	
	public DespachoLaiResponse(DespachoLaiDto despachoLaiDto) {
		this.despachoLaiDto = despachoLaiDto;
	}


	public DespachoLaiDto getDespachoLaiDto() {
		return despachoLaiDto;
	}


	public void setDespachoLaiDto(DespachoLaiDto despachoLaiDto) {
		this.despachoLaiDto = despachoLaiDto;
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.DespachoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Despacho Response")
public class DespachoResponse {
	
	@ApiModelProperty(value = "Despacho Dto")
	private DespachoDto despachoDto;
	
	public DespachoResponse(DespachoDto despachoDto) {
		this.despachoDto = despachoDto;
	}


	public DespachoDto getDespachoDto() {
		return despachoDto;
	}


	public void setDespachoDto(DespachoDto despachoDto) {
		this.despachoDto = despachoDto;
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

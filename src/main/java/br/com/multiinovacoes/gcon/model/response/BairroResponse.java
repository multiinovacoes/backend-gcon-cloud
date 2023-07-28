package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.BairroDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Bairro Response")
public class BairroResponse {
	
	@ApiModelProperty(value = "Bairro Dto")
	private BairroDto bairroDto;
	
	public BairroResponse(BairroDto bairroDto) {
		this.bairroDto = bairroDto;
	}

	public BairroDto getBairroDto() {
		return bairroDto;
	}

	public void setBairroDto(BairroDto bairroDto) {
		this.bairroDto = bairroDto;
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

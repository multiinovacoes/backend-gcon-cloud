package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Encaminhamento Response")
public class EncaminhamentoResponse {
	
	@ApiModelProperty(value = "EncaminhamentoDto Dto")
	private EncaminhamentoDto encaminhamentoDto;
	
	public EncaminhamentoResponse(EncaminhamentoDto encaminhamentoDto) {
		this.encaminhamentoDto = encaminhamentoDto;
	}




	public EncaminhamentoDto getEncaminhamentoDto() {
		return encaminhamentoDto;
	}




	public void setEncaminhamentoDto(EncaminhamentoDto encaminhamentoDto) {
		this.encaminhamentoDto = encaminhamentoDto;
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

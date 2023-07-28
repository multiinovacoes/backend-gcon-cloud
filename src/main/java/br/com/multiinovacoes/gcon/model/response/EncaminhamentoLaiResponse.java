package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Encaminhamento Lai Response")
public class EncaminhamentoLaiResponse {
	
	@ApiModelProperty(value = "EncaminhamentoLaiDto Dto")
	private EncaminhamentoLaiDto encaminhamentoLaiDto;
	
	public EncaminhamentoLaiResponse(EncaminhamentoLaiDto encaminhamentoLaiDto) {
		this.encaminhamentoLaiDto = encaminhamentoLaiDto;
	}




	public EncaminhamentoLaiDto getEncaminhamentoLaiDto() {
		return encaminhamentoLaiDto;
	}




	public void setEncaminhamentoLaiDto(EncaminhamentoLaiDto encaminhamentoLaiDto) {
		this.encaminhamentoLaiDto = encaminhamentoLaiDto;
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

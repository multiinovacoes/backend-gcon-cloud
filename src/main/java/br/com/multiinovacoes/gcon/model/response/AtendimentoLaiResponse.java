package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.AtendimentoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Atendimento Lai Response")
public class AtendimentoLaiResponse {

	
	@ApiModelProperty(value = "Atendimento Lai Dto")
	private AtendimentoLaiDto atendimentoLaiDto;
	
	public AtendimentoLaiResponse(AtendimentoLaiDto atendimentoLaiDto) {
		this.atendimentoLaiDto = atendimentoLaiDto;
	}

	public AtendimentoLaiDto getAtendimentoLaiDto() {
		return atendimentoLaiDto;
	}

	public void setAtendimentoLaiDto(AtendimentoLaiDto atendimentoLaiDto) {
		this.atendimentoLaiDto = atendimentoLaiDto;
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

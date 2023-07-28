package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.AtendimentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Atendimento Response")
public class AtendimentoResponse {
	
	@ApiModelProperty(value = "Atendimento Dto")
	private AtendimentoDto atendimentoDto;
	
	public AtendimentoResponse(AtendimentoDto atendimentoDto) {
		this.atendimentoDto = atendimentoDto;
	}

	public AtendimentoDto getAtendimentoDto() {
		return atendimentoDto;
	}

	public void setAtendimentoDto(AtendimentoDto atendimentoDto) {
		this.atendimentoDto = atendimentoDto;
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Encaminhamento Resposta Response")
public class EncaminhamentoRespostaResponse {
	
	@ApiModelProperty(value = "EncaminhamentoRespostaDto Dto")
	private EncaminhamentoRespostaDto encaminhamentoRespostaDto;
	
	public EncaminhamentoRespostaResponse(EncaminhamentoRespostaDto encaminhamentoRespostaDto) {
		this.encaminhamentoRespostaDto = encaminhamentoRespostaDto;
	}

	public EncaminhamentoRespostaDto getEncaminhamentoRespostaDto() {
		return encaminhamentoRespostaDto;
	}

	public void setEncaminhamentoRespostaDto(EncaminhamentoRespostaDto encaminhamentoRespostaDto) {
		this.encaminhamentoRespostaDto = encaminhamentoRespostaDto;
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

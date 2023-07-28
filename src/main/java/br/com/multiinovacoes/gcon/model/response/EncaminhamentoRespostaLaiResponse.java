package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoRespostaLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Encaminhamento Resposta Lai Response")
public class EncaminhamentoRespostaLaiResponse {
	
	@ApiModelProperty(value = "Encaminhamento Resposta Lai Dto")
	private EncaminhamentoRespostaLaiDto encaminhamentoRespostaLaiDto;
	
	public EncaminhamentoRespostaLaiResponse(EncaminhamentoRespostaLaiDto encaminhamentoRespostaLaiDto) {
		this.encaminhamentoRespostaLaiDto = encaminhamentoRespostaLaiDto;
	}






	public EncaminhamentoRespostaLaiDto getEncaminhamentoRespostaLaiDto() {
		return encaminhamentoRespostaLaiDto;
	}






	public void setEncaminhamentoRespostaLaiDto(EncaminhamentoRespostaLaiDto encaminhamentoRespostaLaiDto) {
		this.encaminhamentoRespostaLaiDto = encaminhamentoRespostaLaiDto;
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

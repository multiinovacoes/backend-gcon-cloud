package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.FormaRespostaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Forma Resposta Response")
public class FormaRespostaResponse {
	
	@ApiModelProperty(value = "Forma Resposta Dto")
	private FormaRespostaDto formaRespostaDto;
	
	public FormaRespostaResponse(FormaRespostaDto formaRespostaDto) {
		this.formaRespostaDto = formaRespostaDto;
	}



	public FormaRespostaDto getFormaRespostaDto() {
		return formaRespostaDto;
	}



	public void setFormaRespostaDto(FormaRespostaDto formaRespostaDto) {
		this.formaRespostaDto = formaRespostaDto;
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

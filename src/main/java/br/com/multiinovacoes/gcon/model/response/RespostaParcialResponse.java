package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.RespostaParcialDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Resposta Parcial Response")
public class RespostaParcialResponse {
	
	@ApiModelProperty(value = "Resposta Parcial Dto")
	private RespostaParcialDto respostaParcialDto;
	
	public RespostaParcialResponse(RespostaParcialDto respostaParcialDto) {
		this.respostaParcialDto = respostaParcialDto;
	}



	public RespostaParcialDto getRespostaParcialDto() {
		return respostaParcialDto;
	}



	public void setRespostaParcialDto(RespostaParcialDto respostaParcialDto) {
		this.respostaParcialDto = respostaParcialDto;
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

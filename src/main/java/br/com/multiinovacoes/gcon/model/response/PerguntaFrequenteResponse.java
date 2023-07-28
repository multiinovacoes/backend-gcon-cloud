package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.PerguntaFrequenteDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Pergunta Frequente Response")
public class PerguntaFrequenteResponse {
	
	@ApiModelProperty(value = "Pergunta Frequente Dto")
	private PerguntaFrequenteDto perguntaFrequenteDto;
	
	public PerguntaFrequenteResponse(PerguntaFrequenteDto perguntaFrequenteDto) {
		this.perguntaFrequenteDto = perguntaFrequenteDto;
	}

	public PerguntaFrequenteDto getPerguntaFrequenteDto() {
		return perguntaFrequenteDto;
	}

	public void setPerguntaFrequenteDto(PerguntaFrequenteDto perguntaFrequenteDto) {
		this.perguntaFrequenteDto = perguntaFrequenteDto;
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

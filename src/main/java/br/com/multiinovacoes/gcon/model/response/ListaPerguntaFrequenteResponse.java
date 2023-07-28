package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.PerguntaFrequenteDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Pergunta Frequente Response")
public class ListaPerguntaFrequenteResponse {
	
	@ApiModelProperty(value = "Lista de Perguntas Frequentes")
	private List<PerguntaFrequenteDto> perguntaFrequenteDtoList;
	
	public ListaPerguntaFrequenteResponse(List<PerguntaFrequenteDto> perguntaFrequenteDtoList) {
		this.perguntaFrequenteDtoList = perguntaFrequenteDtoList;
	}


	public List<PerguntaFrequenteDto> getPerguntaFrequenteDtoList() {
		return perguntaFrequenteDtoList;
	}


	public void setPerguntaFrequenteDtoList(List<PerguntaFrequenteDto> perguntaFrequenteDtoList) {
		this.perguntaFrequenteDtoList = perguntaFrequenteDtoList;
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

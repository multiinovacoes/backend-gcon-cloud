package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.RespostaParcialDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Resposta Parcial Response")
public class ListaRespostaParcialResponse {
	
	@ApiModelProperty(value = "Lista de Resposta Parcial")
	private List<RespostaParcialDto> respostaParcialDtoList;
	
	public ListaRespostaParcialResponse(List<RespostaParcialDto> respostaParcialDtoList) {
		this.respostaParcialDtoList = respostaParcialDtoList;
	}


	public List<RespostaParcialDto> getRespostaParcialDtoList() {
		return respostaParcialDtoList;
	}


	public void setRespostaParcialDtoList(List<RespostaParcialDto> respostaParcialDtoList) {
		this.respostaParcialDtoList = respostaParcialDtoList;
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

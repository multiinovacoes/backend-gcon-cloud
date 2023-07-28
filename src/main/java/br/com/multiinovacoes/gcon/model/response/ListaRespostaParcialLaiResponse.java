package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.RespostaParcialLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Resposta Parcial Lai Response")
public class ListaRespostaParcialLaiResponse {
	
	@ApiModelProperty(value = "Lista de Resposta Parcial Lai")
	private List<RespostaParcialLaiDto> respostaParcialLaiDtoList;
	
	public ListaRespostaParcialLaiResponse(List<RespostaParcialLaiDto> respostaParcialLaiDtoList) {
		this.respostaParcialLaiDtoList = respostaParcialLaiDtoList;
	}


	public List<RespostaParcialLaiDto> getRespostaParcialLaiDtoList() {
		return respostaParcialLaiDtoList;
	}


	public void setRespostaParcialLaiDtoList(List<RespostaParcialLaiDto> respostaParcialLaiDtoList) {
		this.respostaParcialLaiDtoList = respostaParcialLaiDtoList;
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

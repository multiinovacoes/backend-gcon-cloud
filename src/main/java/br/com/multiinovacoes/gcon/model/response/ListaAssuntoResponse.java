package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.AssuntoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Assunto Response")
public class ListaAssuntoResponse {
	
	@ApiModelProperty(value = "Lista de Assuntos")
	private List<AssuntoDto> assuntoDtoList;
	
	public ListaAssuntoResponse(List<AssuntoDto> assuntoDtoList) {
		this.assuntoDtoList = assuntoDtoList;
	}

	public List<AssuntoDto> getAssuntoDtoList() {
		return assuntoDtoList;
	}

	public void setAssuntoDtoList(List<AssuntoDto> assuntoDtoList) {
		this.assuntoDtoList = assuntoDtoList;
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

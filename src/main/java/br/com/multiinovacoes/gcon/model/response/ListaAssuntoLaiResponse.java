package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.AssuntoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Assunto Lai Response")
public class ListaAssuntoLaiResponse {
	
	@ApiModelProperty(value = "Lista de Assunto Lai")
	private List<AssuntoLaiDto> assuntoDtoList;
	
	public ListaAssuntoLaiResponse(List<AssuntoLaiDto> assuntoDtoList) {
		this.assuntoDtoList = assuntoDtoList;
	}

	public List<AssuntoLaiDto> getAssuntoDtoList() {
		return assuntoDtoList;
	}

	public void setAssuntoDtoList(List<AssuntoLaiDto> assuntoDtoList) {
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

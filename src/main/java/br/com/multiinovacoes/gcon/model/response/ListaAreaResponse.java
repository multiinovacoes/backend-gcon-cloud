package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.AreaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Area Response")
public class ListaAreaResponse {
	
	@ApiModelProperty(value = "Lista de Áreas")
	private List<AreaDto> areaDtoList;
	
	public ListaAreaResponse(List<AreaDto> areaDtoList) {
		this.areaDtoList = areaDtoList;
	}

	public List<AreaDto> getAreaDtoList() {
		return areaDtoList;
	}

	public void setAreaDtoList(List<AreaDto> areaDtoList) {
		this.areaDtoList = areaDtoList;
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

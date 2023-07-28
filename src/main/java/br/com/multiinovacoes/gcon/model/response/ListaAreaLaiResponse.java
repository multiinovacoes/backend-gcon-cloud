package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.AreaLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Area Lai Response")
public class ListaAreaLaiResponse {
	
	@ApiModelProperty(value = "Lista de Áreas Lai")
	private List<AreaLaiDto> areaDtoList;
	
	public ListaAreaLaiResponse(List<AreaLaiDto> areaDtoList) {
		this.areaDtoList = areaDtoList;
	}

	public List<AreaLaiDto> getAreaDtoList() {
		return areaDtoList;
	}

	public void setAreaDtoList(List<AreaLaiDto> areaDtoList) {
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

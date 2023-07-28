package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.DespachoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Despacho Response")
public class ListaDespachoResponse {
	
	@ApiModelProperty(value = "Lista de Despachos")
	private List<DespachoDto> despachoDtoList;
	
	public ListaDespachoResponse(List<DespachoDto> despachoDtoList) {
		this.despachoDtoList = despachoDtoList;
	}



	public List<DespachoDto> getDespachoDtoList() {
		return despachoDtoList;
	}



	public void setDespachoDtoList(List<DespachoDto> despachoDtoList) {
		this.despachoDtoList = despachoDtoList;
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

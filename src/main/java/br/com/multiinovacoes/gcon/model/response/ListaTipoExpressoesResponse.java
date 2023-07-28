package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.TipoExpressoesDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Tipo Expressões Response")
public class ListaTipoExpressoesResponse {
	
	@ApiModelProperty(value = "Lista de Tipo Expressões")
	private List<TipoExpressoesDto> tipoExpressoesDtoList;
	
	public ListaTipoExpressoesResponse(List<TipoExpressoesDto> tipoExpressoesDtoList) {
		this.tipoExpressoesDtoList = tipoExpressoesDtoList;
	}


	public List<TipoExpressoesDto> getTipoExpressoesDtoList() {
		return tipoExpressoesDtoList;
	}


	public void setTipoExpressoesDtoList(List<TipoExpressoesDto> tipoExpressoesDtoList) {
		this.tipoExpressoesDtoList = tipoExpressoesDtoList;
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

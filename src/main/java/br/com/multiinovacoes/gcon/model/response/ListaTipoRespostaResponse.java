package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.TipoRespostaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Tipo Resposta Response")
public class ListaTipoRespostaResponse {
	
	@ApiModelProperty(value = "Lista de Tipos de Respostas")
	private List<TipoRespostaDto> tipoRespostaDtoList;
	
	public ListaTipoRespostaResponse(List<TipoRespostaDto> tipoRespostaDtoList) {
		this.tipoRespostaDtoList = tipoRespostaDtoList;
	}

	public List<TipoRespostaDto> getTipoRespostaDtoList() {
		return tipoRespostaDtoList;
	}

	public void setTipoRespostaDtoList(List<TipoRespostaDto> tipoRespostaDtoList) {
		this.tipoRespostaDtoList = tipoRespostaDtoList;
	}


	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {  
		return super.hashCode();  
	}
	
	
	

}

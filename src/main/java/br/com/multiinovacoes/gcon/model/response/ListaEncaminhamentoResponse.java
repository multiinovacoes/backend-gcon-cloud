package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Encaminhamento Response")
public class ListaEncaminhamentoResponse {
	
	@ApiModelProperty(value = "Lista de Encaminhamentos")
	private List<EncaminhamentoDto> encaminhamentoDtoList;
	
	public ListaEncaminhamentoResponse(List<EncaminhamentoDto> encaminhamentoDtoList) {
		this.encaminhamentoDtoList = encaminhamentoDtoList;
	}


	public List<EncaminhamentoDto> getEncaminhamentoDtoList() {
		return encaminhamentoDtoList;
	}


	public void setEncaminhamentoDtoList(List<EncaminhamentoDto> encaminhamentoDtoList) {
		this.encaminhamentoDtoList = encaminhamentoDtoList;
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

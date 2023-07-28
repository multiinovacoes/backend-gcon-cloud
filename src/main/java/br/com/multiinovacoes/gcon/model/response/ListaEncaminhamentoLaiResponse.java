package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.EncaminhamentoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Encaminhamento Lai Response")
public class ListaEncaminhamentoLaiResponse {
	
	@ApiModelProperty(value = "Lista de Encaminhamentos Lai")
	private List<EncaminhamentoLaiDto> encaminhamentoLaiDtoList;
	
	public ListaEncaminhamentoLaiResponse(List<EncaminhamentoLaiDto> encaminhamentoLaiDtoList) {
		this.encaminhamentoLaiDtoList = encaminhamentoLaiDtoList;
	}


	public List<EncaminhamentoLaiDto> getEncaminhamentoLaiDtoList() {
		return encaminhamentoLaiDtoList;
	}


	public void setEncaminhamentoLaiDtoList(List<EncaminhamentoLaiDto> encaminhamentoLaiDtoList) {
		this.encaminhamentoLaiDtoList = encaminhamentoLaiDtoList;
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

package br.com.multiinovacoes.gcon.model.response;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.AtendimentoLai;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Atendimento Lai Response")
public class ListaAtendimentoLaiResponse {
	
	@ApiModelProperty(value = "Lista de Atendimentos Lai")
	private Page<AtendimentoLai> atendimentoLaiDtoList;
	
	public ListaAtendimentoLaiResponse(Page<AtendimentoLai> atendimentoLaiDtoList) {
		this.atendimentoLaiDtoList = atendimentoLaiDtoList;
	}

	public Page<AtendimentoLai> getAtendimentoLaiDtoList() {
		return atendimentoLaiDtoList;
	}

	public void setAtendimentoLaiDtoList(Page<AtendimentoLai> atendimentoLaiDtoList) {
		this.atendimentoLaiDtoList = atendimentoLaiDtoList;
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

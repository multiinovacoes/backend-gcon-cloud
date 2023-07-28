package br.com.multiinovacoes.gcon.model.response;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.Atendimento;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Atendimento Response")
public class ListaAtendimentoResponse {
	
	@ApiModelProperty(value = "Lista de Atendimentos")
	private Page<Atendimento> atendimentoDtoList;
	
	public ListaAtendimentoResponse(Page<Atendimento> atendimentoDtoList) {
		this.atendimentoDtoList = atendimentoDtoList;
	}

	public Page<Atendimento> getAtendimentoDtoList() {
		return atendimentoDtoList;
	}

	public void setAtendimentoDtoList(Page<Atendimento> atendimentoDtoList) {
		this.atendimentoDtoList = atendimentoDtoList;
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

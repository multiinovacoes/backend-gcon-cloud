package br.com.multiinovacoes.gcon.model.relatorio.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.report.AtendimentoArea;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Area Response")
public class ListaAtendimentoAreaResponse {
	
	@ApiModelProperty(value = "Lista de Relatório de Atendimento por Area")
	private List<AtendimentoArea> atendimentoAreaList;
	
	public ListaAtendimentoAreaResponse(List<AtendimentoArea> atendimentoAreaList) {
		this.atendimentoAreaList = atendimentoAreaList;
	}


	public List<AtendimentoArea> getAtendimentoAreaList() {
		return atendimentoAreaList;
	}


	public void setAtendimentoAreaList(List<AtendimentoArea> atendimentoAreaList) {
		this.atendimentoAreaList = atendimentoAreaList;
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

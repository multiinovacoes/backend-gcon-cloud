package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.LogAtendimentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Log Atendimento Response")
public class ListaLogAtendimentoResponse {
	
	@ApiModelProperty(value = "Lista de Log Atendimento")
	private List<LogAtendimentoDto> logAtendimentoDtoList;
	
	public ListaLogAtendimentoResponse(List<LogAtendimentoDto> logAtendimentoDtoList) {
		this.logAtendimentoDtoList = logAtendimentoDtoList;
	}
	

	public List<LogAtendimentoDto> getLogAtendimentoDtoList() {
		return logAtendimentoDtoList;
	}

	public void setLogAtendimentoDtoList(List<LogAtendimentoDto> logAtendimentoDtoList) {
		this.logAtendimentoDtoList = logAtendimentoDtoList;
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

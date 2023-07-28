package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.FeriadoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Feriado Response")
public class ListaFeriadoResponse {
	
	@ApiModelProperty(value = "Lista de Feriados")
	private List<FeriadoDto> feriadoDtoList;
	
	public ListaFeriadoResponse(List<FeriadoDto> feriadoDtoList) {
		this.feriadoDtoList = feriadoDtoList;
	}

	public List<FeriadoDto> getFeriadoDtoList() {
		return feriadoDtoList;
	}

	public void setFeriadoDtoList(List<FeriadoDto> feriadoDtoList) {
		this.feriadoDtoList = feriadoDtoList;
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

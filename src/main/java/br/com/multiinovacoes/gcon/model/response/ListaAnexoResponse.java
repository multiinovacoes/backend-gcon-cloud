package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.AnexoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Anexo Response")
public class ListaAnexoResponse {
	
	@ApiModelProperty(value = "Lista de Anexos")
	private List<AnexoDto> anexoDtoList;

	public ListaAnexoResponse(List<AnexoDto> anexoDtoList) {
		super();
		this.anexoDtoList = anexoDtoList;
	}

	public List<AnexoDto> getAnexoDtoList() {
		return anexoDtoList;
	}

	public void setAnexoDtoList(List<AnexoDto> anexoDtoList) {
		this.anexoDtoList = anexoDtoList;
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

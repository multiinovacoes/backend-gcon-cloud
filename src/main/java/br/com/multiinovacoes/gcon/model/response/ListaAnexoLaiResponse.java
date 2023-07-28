package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.AnexoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Anexo Lai Response")
public class ListaAnexoLaiResponse {
	
	@ApiModelProperty(value = "Lista de Anexos Lai")
	private List<AnexoLaiDto> anexoLaiDtoList;

	public ListaAnexoLaiResponse(List<AnexoLaiDto> anexoLaiDtoList) {
		super();
		this.anexoLaiDtoList = anexoLaiDtoList;
	}

	public List<AnexoLaiDto> getAnexoLaiDtoList() {
		return anexoLaiDtoList;
	}

	public void setAnexoLaiDtoList(List<AnexoLaiDto> anexoLaiDtoList) {
		this.anexoLaiDtoList = anexoLaiDtoList;
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

package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.SetorDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Setor Response")
public class ListaSetorResponse {
	
	@ApiModelProperty(value = "Lista de Setores")
	private List<SetorDto> setorDtoList;
	
	public ListaSetorResponse(List<SetorDto> setorDtoList) {
		this.setorDtoList = setorDtoList;
	}


	public List<SetorDto> getSetorDtoList() {
		return setorDtoList;
	}


	public void setSetorDtoList(List<SetorDto> setorDtoList) {
		this.setorDtoList = setorDtoList;
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

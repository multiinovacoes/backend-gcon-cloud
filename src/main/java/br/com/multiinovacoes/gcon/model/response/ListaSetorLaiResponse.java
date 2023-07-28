package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.SetorLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Setor Lai Response")
public class ListaSetorLaiResponse {
	
	@ApiModelProperty(value = "Lista de Setores Lai")
	private List<SetorLaiDto> setorLaiDtoList;
	
	public ListaSetorLaiResponse(List<SetorLaiDto> setorLaiDtoList) {
		this.setorLaiDtoList = setorLaiDtoList;
	}


	public List<SetorLaiDto> getSetorLaiDtoList() {
		return setorLaiDtoList;
	}


	public void setSetorLaiDtoList(List<SetorLaiDto> setorLaiDtoList) {
		this.setorLaiDtoList = setorLaiDtoList;
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

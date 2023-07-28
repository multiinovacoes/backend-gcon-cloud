package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.InstituicaoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Instituição Response")
public class ListaInstituicaoResponse {
	
	@ApiModelProperty(value = "Lista de Instituições")
	private List<InstituicaoDto> instituicaoDtoList;
	
	public ListaInstituicaoResponse(List<InstituicaoDto> instituicaoDtoList) {
		this.instituicaoDtoList = instituicaoDtoList;
	}

	public List<InstituicaoDto> getInstituicaoDtoList() {
		return instituicaoDtoList;
	}

	public void setInstituicaoDtoList(List<InstituicaoDto> instituicaoDtoList) {
		this.instituicaoDtoList = instituicaoDtoList;
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

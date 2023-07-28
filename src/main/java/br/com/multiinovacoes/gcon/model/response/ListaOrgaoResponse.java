package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.OrgaoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Orgão Response")
public class ListaOrgaoResponse {

	@ApiModelProperty(value = "Lista de Orgãos")
	private List<OrgaoDto> orgaoDtoList;
	
	public ListaOrgaoResponse(List<OrgaoDto> orgaoDtoList) {
		this.orgaoDtoList = orgaoDtoList;
	}

	public List<OrgaoDto> getOrgaoDtoList() {
		return orgaoDtoList;
	}

	public void setOrgaoDtoList(List<OrgaoDto> orgaoDtoList) {
		this.orgaoDtoList = orgaoDtoList;
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

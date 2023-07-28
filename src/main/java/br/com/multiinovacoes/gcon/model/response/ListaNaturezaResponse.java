package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.NaturezaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Natureza Response")
public class ListaNaturezaResponse {
	
	@ApiModelProperty(value = "Lista de Naturezas")
	private List<NaturezaDto> naturezaDtoList;
	
	public ListaNaturezaResponse(List<NaturezaDto> naturezaDtoList) {
		this.naturezaDtoList = naturezaDtoList;
	}

	public List<NaturezaDto> getNaturezaDtoList() {
		return naturezaDtoList;
	}

	public void setNaturezaDtoList(List<NaturezaDto> naturezaDtoList) {
		this.naturezaDtoList = naturezaDtoList;
	}


	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {  
		return super.hashCode();  
	}
	
	
	

}

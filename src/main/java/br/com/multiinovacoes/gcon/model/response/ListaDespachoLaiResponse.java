package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.DespachoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Despacho Lai Response")
public class ListaDespachoLaiResponse {
	
	@ApiModelProperty(value = "Lista de Despachos Lai")
	private List<DespachoLaiDto> despachoLaiDtoList;
	
	public ListaDespachoLaiResponse(List<DespachoLaiDto> despachoLaiDtoList) {
		this.despachoLaiDtoList = despachoLaiDtoList;
	}



	public List<DespachoLaiDto> getDespachoLaiDtoList() {
		return despachoLaiDtoList;
	}



	public void setDespachoLaiDtoList(List<DespachoLaiDto> despachoLaiDtoList) {
		this.despachoLaiDtoList = despachoLaiDtoList;
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

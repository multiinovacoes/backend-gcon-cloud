package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Modelos Documentos Lai Response")
public class ListaModeloDocumentoLaiResponse {
	
	@ApiModelProperty(value = "Lista de Modelos Documentos Lai")
	private List<ModeloDocumentoLaiDto> modeloDocumentoLaiDtoList;
	
	public ListaModeloDocumentoLaiResponse(List<ModeloDocumentoLaiDto> modeloDocumentoLaiDtoList) {
		this.modeloDocumentoLaiDtoList = modeloDocumentoLaiDtoList;
	}


	public List<ModeloDocumentoLaiDto> getModeloDocumentoLaiDtoList() {
		return modeloDocumentoLaiDtoList;
	}


	public void setModeloDocumentoLaiDtoList(List<ModeloDocumentoLaiDto> modeloDocumentoLaiDtoList) {
		this.modeloDocumentoLaiDtoList = modeloDocumentoLaiDtoList;
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

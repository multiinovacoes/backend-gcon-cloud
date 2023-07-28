package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Modelos Documentos Response")
public class ListaModeloDocumentoResponse {
	
	@ApiModelProperty(value = "Lista de Modelos Documentos")
	private List<ModeloDocumentoDto> modeloDocumentoDtoList;
	
	public ListaModeloDocumentoResponse(List<ModeloDocumentoDto> modeloDocumentoDtoList) {
		this.modeloDocumentoDtoList = modeloDocumentoDtoList;
	}


	public List<ModeloDocumentoDto> getModeloDocumentoDtoList() {
		return modeloDocumentoDtoList;
	}


	public void setModeloDocumentoDtoList(List<ModeloDocumentoDto> modeloDocumentoDtoList) {
		this.modeloDocumentoDtoList = modeloDocumentoDtoList;
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

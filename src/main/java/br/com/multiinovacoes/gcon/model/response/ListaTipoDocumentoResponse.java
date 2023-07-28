package br.com.multiinovacoes.gcon.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.multiinovacoes.gcon.model.dto.TipoDocumentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@JsonInclude
@ApiModel("Lista Tipo Documento Response")
public class ListaTipoDocumentoResponse {
	
	@ApiModelProperty(value = "Lista de Tipos Documentos")
	private List<TipoDocumentoDto> tipoDocumentoDtoList;
	
	public ListaTipoDocumentoResponse(List<TipoDocumentoDto> tipoDocumentoDtoList) {
		this.tipoDocumentoDtoList = tipoDocumentoDtoList;
	}


	public List<TipoDocumentoDto> getTipoDocumentoDtoList() {
		return tipoDocumentoDtoList;
	}


	public void setTipoDocumentoDtoList(List<TipoDocumentoDto> tipoDocumentoDtoList) {
		this.tipoDocumentoDtoList = tipoDocumentoDtoList;
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

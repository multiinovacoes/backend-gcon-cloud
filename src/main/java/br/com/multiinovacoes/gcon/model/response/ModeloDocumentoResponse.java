package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Area Response")
public class ModeloDocumentoResponse {
	
	@ApiModelProperty(value = "Modelo Documento Dto")
	private ModeloDocumentoDto modeloDocumentoDto;
	
	private String resposta;
	
	public ModeloDocumentoResponse(ModeloDocumentoDto modeloDocumentoDto) {
		this.modeloDocumentoDto = modeloDocumentoDto;
	}

	public ModeloDocumentoResponse(String resposta) {
		this.resposta = resposta;
	}

	public ModeloDocumentoDto getModeloDocumentoDto() {
		return modeloDocumentoDto;
	}

	public void setModeloDocumentoDto(ModeloDocumentoDto modeloDocumentoDto) {
		this.modeloDocumentoDto = modeloDocumentoDto;
	}




	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
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

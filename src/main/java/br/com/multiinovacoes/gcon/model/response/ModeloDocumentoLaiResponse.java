package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.ModeloDocumentoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Modelo Documento Lai Response")
public class ModeloDocumentoLaiResponse {
	
	@ApiModelProperty(value = "Modelo Documento Lai Dto")
	private ModeloDocumentoLaiDto modeloDocumentoLaiDto;
	
	private String resposta;
	
	public ModeloDocumentoLaiResponse(ModeloDocumentoLaiDto modeloDocumentoLaiDto) {
		this.modeloDocumentoLaiDto = modeloDocumentoLaiDto;
	}

	public ModeloDocumentoLaiResponse(String resposta) {
		this.resposta = resposta;
	}

	public ModeloDocumentoLaiDto getModeloDocumentoLaiDto() {
		return modeloDocumentoLaiDto;
	}

	public void setModeloDocumentoDto(ModeloDocumentoLaiDto modeloDocumentoLaiDto) {
		this.modeloDocumentoLaiDto = modeloDocumentoLaiDto;
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.TipoDocumentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Tipo Documento Response")
public class TipoDocumentoResponse {
	
	@ApiModelProperty(value = "Tipo Documento Dto")
	private TipoDocumentoDto tipoDocumentoDto;
	
	public TipoDocumentoResponse(TipoDocumentoDto tipoDocumentoDto) {
		this.tipoDocumentoDto = tipoDocumentoDto;
	}

	public TipoDocumentoDto getTipoDocumentoDto() {
		return tipoDocumentoDto;
	}

	public void setTipoDocumentoDto(TipoDocumentoDto tipoDocumentoDto) {
		this.tipoDocumentoDto = tipoDocumentoDto;
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

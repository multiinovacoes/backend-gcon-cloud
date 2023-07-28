package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.TipoExpressoesDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Tipo Expressoes Response")
public class TipoExpressoesResponse {
	
	@ApiModelProperty(value = "Tipo Expressões Dto")
	private TipoExpressoesDto tipoExpressoesDto;
	
	public TipoExpressoesResponse(TipoExpressoesDto tipoExpressoesDto) {
		this.tipoExpressoesDto = tipoExpressoesDto;
	}

	public TipoExpressoesDto getTipoExpressoesDto() {
		return tipoExpressoesDto;
	}

	public void setTipoExpressoesDto(TipoExpressoesDto tipoExpressoesDto) {
		this.tipoExpressoesDto = tipoExpressoesDto;
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

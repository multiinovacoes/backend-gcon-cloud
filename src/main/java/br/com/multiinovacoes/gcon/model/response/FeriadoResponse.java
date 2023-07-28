package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.FeriadoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Feriado Response")
public class FeriadoResponse {
	
	@ApiModelProperty(value = "Feriado Dto")
	private FeriadoDto feriadoDto;
	
	public FeriadoResponse(FeriadoDto feriadoDto) {
		this.feriadoDto = feriadoDto;
	}


	public FeriadoDto getFeriadoDto() {
		return feriadoDto;
	}


	public void setFeriadoDto(FeriadoDto feriadoDto) {
		this.feriadoDto = feriadoDto;
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

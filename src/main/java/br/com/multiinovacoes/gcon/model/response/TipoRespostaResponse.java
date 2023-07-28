package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.TipoRespostaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Tipo Resposta Response")
public class TipoRespostaResponse {
	
    @ApiModelProperty(value = "Tipo Resposta Dto")
	TipoRespostaDto tipoRespostaDto;
	
	public TipoRespostaResponse(TipoRespostaDto tipoRespostaDto) {
		this.tipoRespostaDto = tipoRespostaDto;
	}
	
	public TipoRespostaDto getTipoRespostaDto() {
		return tipoRespostaDto;
	}

	public void setTipoRespostaDto(TipoRespostaDto tipoRespostaDto) {
		this.tipoRespostaDto = tipoRespostaDto;
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

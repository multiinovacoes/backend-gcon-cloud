package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.LogoTipoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Logo Tipo Response")
public class LogoTipoResponse {
	
	@ApiModelProperty(value = "Logo Tipo Dto")
	private LogoTipoDto logoTipoDto;
	
	public LogoTipoResponse(LogoTipoDto logoTipoDto) {
		this.logoTipoDto = logoTipoDto;
	}

	public LogoTipoDto getLogoTipoDto() {
		return logoTipoDto;
	}

	public void setLogoTipoDto(LogoTipoDto logoTipoDto) {
		this.logoTipoDto = logoTipoDto;
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

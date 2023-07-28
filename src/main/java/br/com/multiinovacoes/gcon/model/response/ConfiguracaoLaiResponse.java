package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoLaiDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Configuração Lai Response")
public class ConfiguracaoLaiResponse {
	
	@ApiModelProperty(value = "Configuração Lai Dto")
	private ConfiguracaoLaiDto configuracaoLaiDto;
	
	public ConfiguracaoLaiResponse(ConfiguracaoLaiDto configuracaoLaiDto) {
		this.configuracaoLaiDto = configuracaoLaiDto;
	}

	public ConfiguracaoLaiDto getConfiguracaoLaiDto() {
		return configuracaoLaiDto;
	}

	public void setConfiguracaoLaiDto(ConfiguracaoLaiDto configuracaoLaiDto) {
		this.configuracaoLaiDto = configuracaoLaiDto;
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

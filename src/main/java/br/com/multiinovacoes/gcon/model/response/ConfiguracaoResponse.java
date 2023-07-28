package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.ConfiguracaoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Configuração Response")
public class ConfiguracaoResponse {
	
	@ApiModelProperty(value = "Configuração Dto")
	private ConfiguracaoDto configuracaoDto;
	
	public ConfiguracaoResponse(ConfiguracaoDto configuracaoDto) {
		this.configuracaoDto = configuracaoDto;
	}

	public ConfiguracaoDto getConfiguracaoDto() {
		return configuracaoDto;
	}

	public void setConfiguracaoDto(ConfiguracaoDto configuracaoDto) {
		this.configuracaoDto = configuracaoDto;
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

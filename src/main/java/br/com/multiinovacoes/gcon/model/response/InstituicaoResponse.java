package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.InstituicaoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Instituição Response")
public class InstituicaoResponse {
	
	@ApiModelProperty(value = "Instituição Dto")
	private InstituicaoDto instituicaoDto;
	
	public InstituicaoResponse(InstituicaoDto instituicaoDto) {
		this.instituicaoDto = instituicaoDto;
	}


	public InstituicaoDto getInstituicaoDto() {
		return instituicaoDto;
	}


	public void setInstituicaoDto(InstituicaoDto instituicaoDto) {
		this.instituicaoDto = instituicaoDto;
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

package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.EmpresaDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Empresa Response")
public class EmpresaResponse {
	
	@ApiModelProperty(value = "Empresa Dto")
	private EmpresaDto empresaDto;
	
	public EmpresaResponse(EmpresaDto empresaDto) {
		this.empresaDto = empresaDto;
	}

	public EmpresaDto getEmpresaDto() {
		return empresaDto;
	}

	public void setEmpresaDto(EmpresaDto empresaDto) {
		this.empresaDto = empresaDto;
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

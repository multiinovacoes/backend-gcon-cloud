package br.com.multiinovacoes.gcon.model.response;

import br.com.multiinovacoes.gcon.model.dto.ResumoAtendimentoDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Resumo Atendimento Response")
public class ResumoAtendimentoResponse {
	
	@ApiModelProperty(value = "Resumo Atendimento Dto")
	private ResumoAtendimentoDto resumoAtendimentoDto;
	
	public ResumoAtendimentoResponse(ResumoAtendimentoDto resumoAtendimentoDto) {
		this.resumoAtendimentoDto = resumoAtendimentoDto;
	}


	public ResumoAtendimentoDto getResumoAtendimentoDto() {
		return resumoAtendimentoDto;
	}


	public void setResumoAtendimentoDto(ResumoAtendimentoDto resumoAtendimentoDto) {
		this.resumoAtendimentoDto = resumoAtendimentoDto;
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

package br.com.multiinovacoes.gcon.model.request;

import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.PesquisaSatisfacaoDto;
import io.swagger.annotations.ApiModelProperty;

public class PesquisaSatisfacaoRequest {
	
	@ApiModelProperty(value = "Id do atendimento")
	private Long idAtendimento;

	private List<PesquisaSatisfacaoDto> listaPergunta;

	public List<PesquisaSatisfacaoDto> getListaPergunta() {
		return listaPergunta;
	}

	public void setListaPergunta(List<PesquisaSatisfacaoDto> listaPergunta) {
		this.listaPergunta = listaPergunta;
	}


	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	

}

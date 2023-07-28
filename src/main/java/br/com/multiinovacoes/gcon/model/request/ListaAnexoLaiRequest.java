package br.com.multiinovacoes.gcon.model.request;

import java.io.Serializable;
import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.ListaAnexoLaiDto;
import io.swagger.annotations.ApiModelProperty;

public class ListaAnexoLaiRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4313343368793462569L;
	
	@ApiModelProperty(value = "Id do atendimento")
	private Long atendimento;
	
	private List<ListaAnexoLaiDto> listaAnexoDto;
	

	public Long getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}

	public List<ListaAnexoLaiDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoLaiDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}
	
	

}

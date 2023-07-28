package br.com.multiinovacoes.gcon.model.request;

import java.io.Serializable;
import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.ListaAnexoDto;
import io.swagger.annotations.ApiModelProperty;

public class ListaAnexoRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4313343368793462569L;
	
	@ApiModelProperty(value = "Id do atendimento")
	private Long codigoAtendimento;
	
	private List<ListaAnexoDto> listaAnexoDto;
	
	private String tipoCliente;
	
	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public Long getCodigoAtendimento() {
		return codigoAtendimento;
	}

	public void setCodigoAtendimento(Long codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}

	public List<ListaAnexoDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}
	
	

}

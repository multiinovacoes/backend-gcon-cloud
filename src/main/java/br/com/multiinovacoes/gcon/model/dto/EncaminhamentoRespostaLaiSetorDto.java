package br.com.multiinovacoes.gcon.model.dto;

import java.util.List;

import br.com.multiinovacoes.gcon.model.response.ListaModeloDocumentoLaiResponse;
import io.swagger.annotations.ApiModelProperty;

public class EncaminhamentoRespostaLaiSetorDto {
	
	
	private String nomeSetorDestino;
	
	private String protocolo;
	
	private ListaModeloDocumentoLaiResponse listaModelo;
	
	private String resposta;
	
	private String parametro;
	
	private boolean respondido;
	
	private Long codigoAtendimento;
	
	@ApiModelProperty(value = "Lista de anexos")
	private List<ListaAnexoDto> listaAnexoDto;

	public List<ListaAnexoDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}

	public String getNomeSetorDestino() {
		return nomeSetorDestino;
	}

	public void setNomeSetorDestino(String nomeSetorDestino) {
		this.nomeSetorDestino = nomeSetorDestino;
	}

	public ListaModeloDocumentoLaiResponse getListaModelo() {
		return listaModelo;
	}

	public void setListaModelo(ListaModeloDocumentoLaiResponse listaModelo) {
		this.listaModelo = listaModelo;
	}

	public String getProtocolo() {
		return protocolo;
	}

	public void setProtocolo(String protocolo) {
		this.protocolo = protocolo;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public boolean isRespondido() {
		return respondido;
	}

	public void setRespondido(boolean respondido) {
		this.respondido = respondido;
	}

	public Long getCodigoAtendimento() {
		return codigoAtendimento;
	}

	public void setCodigoAtendimento(Long codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}

	

}

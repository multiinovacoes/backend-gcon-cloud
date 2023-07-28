package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;

public class ListaAnexoLaiDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4313343368793462569L;

	private String stringBase64;
	
	private String nomeArquivo;

	public String getStringBase64() {
		return stringBase64;
	}

	public void setStringBase64(String stringBase64) {
		this.stringBase64 = stringBase64;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	
	

}

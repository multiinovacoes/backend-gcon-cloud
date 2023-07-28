package br.com.multiinovacoes.gcon.model;

import java.time.LocalDate;

public class ModeloListaEncaminhamentoRecebidos {
	
	
	private Long idAtendimento;
	
	private String numeroProtocolo;
	
	private String setorDestino;
	
	private String assunto;
	
	private String prioridade;
	
	private LocalDate data;
	

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getSetorDestino() {
		return setorDestino;
	}

	public void setSetorDestino(String setorDestino) {
		this.setorDestino = setorDestino;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(String prioridade) {
		this.prioridade = prioridade;
	}
	
	

}

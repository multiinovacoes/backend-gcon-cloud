package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;
import java.util.List;

import br.com.multiinovacoes.gcon.model.PerguntaSatisfacao;

public class ResumoAtendimentoDto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2051779210465713988L;
	
	private String numeroProtocolo;

	private String nomeSolicitante;
	
	private String situacao;
	
	private String natureza;
	
	private String assunto;
	
	private String bairroOcorrencia;
	
	private String descricaoFatos;
	
	private String respostaConclusao;
	
	private String dataCriacao;
	
	private Integer respondeuPesquisa;
	
	private String manifestacaoConcluida;
	
	private Long idAtendimento;
	
	private List<RespostaParcialDto> listaResposta;
	
	private List<PerguntaSatisfacao> listaPergunta;

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getManifestacaoConcluida() {
		return manifestacaoConcluida;
	}

	public void setManifestacaoConcluida(String manifestacaoConcluida) {
		this.manifestacaoConcluida = manifestacaoConcluida;
	}

	public List<PerguntaSatisfacao> getListaPergunta() {
		return listaPergunta;
	}

	public void setListaPergunta(List<PerguntaSatisfacao> listaPergunta) {
		this.listaPergunta = listaPergunta;
	}

	public Long getIdAtendimento() {
		return idAtendimento;
	}

	public void setIdAtendimento(Long idAtendimento) {
		this.idAtendimento = idAtendimento;
	}

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getDescricaoFatos() {
		return descricaoFatos;
	}

	public void setDescricaoFatos(String descricaoFatos) {
		this.descricaoFatos = descricaoFatos;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public List<RespostaParcialDto> getListaResposta() {
		return listaResposta;
	}

	public void setListaResposta(List<RespostaParcialDto> listaResposta) {
		this.listaResposta = listaResposta;
	}

	public String getRespostaConclusao() {
		return respostaConclusao;
	}

	public void setRespostaConclusao(String respostaConclusao) {
		this.respostaConclusao = respostaConclusao;
	}

	public Integer getRespondeuPesquisa() {
		return respondeuPesquisa;
	}

	public void setRespondeuPesquisa(Integer respondeuPesquisa) {
		this.respondeuPesquisa = respondeuPesquisa;
	}

	public String getBairroOcorrencia() {
		return bairroOcorrencia;
	}

	public void setBairroOcorrencia(String bairroOcorrencia) {
		this.bairroOcorrencia = bairroOcorrencia;
	}
	
	


}

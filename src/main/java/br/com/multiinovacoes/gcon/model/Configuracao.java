package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "OUVIDORIA_CONFIGURACOES")
public class Configuracao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "INCODIGO")
	private Long id;

	@Column(name = "INQTDDIASVENCER")
	private Integer qtdDiasVencer;
	
	@Column(name = "INQTDDIASDESVIOATENDIMENTO")
	private Integer qtdDiasDesvioAtendimento;
	
	@Column(name = "INQTDDIASDESVIOENCAMINHAMENTO")
	private Integer qtdDiasDesvioEncaminhamento;
	
	@Column(name = "INQTDDIASDESVIOMARCO")
	private Integer qtdDiasDesvioMarco;
	
	@Column(name = "INQTDDIASENCAMINHAMENTO")
	private Integer qtdDiasEncaminhamento;
	
	@Column(name = "INQTDDIASRESPOSTA")
	private Integer qtdDiasResposta;
	
	@Column(name = "INQTDDIASALERTAMARCO")
	private Integer qtdDiasAlertaMarco;
	
	@Column(name = "INQTDDIASALERTAENCAMINHAMENTO")
	private Integer qtdDiasAlertaEncaminhamento;
	
	@Column(name = "INQTDDIASEXIBIR")
	private Integer qtdDiasExibirAtendimentosEnviados;
	
	@Column(name = "IN_INTERVALO_COBRANCA")
	private Integer qtdIntervaloDiasEmail;
	
	@Column(name = "INCODIGOORGAO")
	private Long orgao;
	
	@Column(name = "INMANTERSIGILO")
	private Boolean manterSigilo;
	
	@Column(name = "INFORMAATENDIMENTO")
	private Integer formaAtendimento;
	
	@Column(name = "INPRIORIDADE")
	private Integer prioridadePadrao;

	@Column(name = "IN_QTD_MAX_PRORROGA_PRAZO")
	private Integer numeroDiasProrrogarAtendimento;

	@Column(name = "SM_VISUALIZA_DETALHES")
	private Boolean exibirDetalheEncaminhamento;
	
	@Column(name = "INRESPOSTA_PADRAO")
	private Boolean enviarRespostaParcial;
	
	@Column(name = "INRESPOSTA_PARCIAL")
	private Integer respostaParcialPadrao;
	
	@Column(name = "INCODIGOSETOR")
	private Long setor;

	@Column(name = "IN_MODELO_PRORROGA_PRAZO")
	private Long respostaParcialPadraoProrrogacao;
	
	public Long getRespostaParcialPadraoProrrogacao() {
		return respostaParcialPadraoProrrogacao;
	}

	public void setRespostaParcialPadraoProrrogacao(Long respostaParcialPadraoProrrogacao) {
		this.respostaParcialPadraoProrrogacao = respostaParcialPadraoProrrogacao;
	}

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
	}

	public Integer getNumeroDiasProrrogarAtendimento() {
		return numeroDiasProrrogarAtendimento;
	}

	public void setNumeroDiasProrrogarAtendimento(Integer numeroDiasProrrogarAtendimento) {
		this.numeroDiasProrrogarAtendimento = numeroDiasProrrogarAtendimento;
	}

	public Integer getQtdDiasExibirAtendimentosEnviados() {
		return qtdDiasExibirAtendimentosEnviados;
	}

	public void setQtdDiasExibirAtendimentosEnviados(Integer qtdDiasExibirAtendimentosEnviados) {
		this.qtdDiasExibirAtendimentosEnviados = qtdDiasExibirAtendimentosEnviados;
	}

	public Integer getQtdIntervaloDiasEmail() {
		return qtdIntervaloDiasEmail;
	}

	public void setQtdIntervaloDiasEmail(Integer qtdIntervaloDiasEmail) {
		this.qtdIntervaloDiasEmail = qtdIntervaloDiasEmail;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getQtdDiasVencer() {
		return qtdDiasVencer;
	}

	public void setQtdDiasVencer(Integer qtdDiasVencer) {
		this.qtdDiasVencer = qtdDiasVencer;
	}

	public Integer getQtdDiasDesvioAtendimento() {
		return qtdDiasDesvioAtendimento;
	}

	public void setQtdDiasDesvioAtendimento(Integer qtdDiasDesvioAtendimento) {
		this.qtdDiasDesvioAtendimento = qtdDiasDesvioAtendimento;
	}

	public Integer getQtdDiasDesvioEncaminhamento() {
		return qtdDiasDesvioEncaminhamento;
	}

	public void setQtdDiasDesvioEncaminhamento(Integer qtdDiasDesvioEncaminhamento) {
		this.qtdDiasDesvioEncaminhamento = qtdDiasDesvioEncaminhamento;
	}

	public Integer getQtdDiasDesvioMarco() {
		return qtdDiasDesvioMarco;
	}

	public void setQtdDiasDesvioMarco(Integer qtdDiasDesvioMarco) {
		this.qtdDiasDesvioMarco = qtdDiasDesvioMarco;
	}

	public Integer getQtdDiasEncaminhamento() {
		return qtdDiasEncaminhamento;
	}

	public void setQtdDiasEncaminhamento(Integer qtdDiasEncaminhamento) {
		this.qtdDiasEncaminhamento = qtdDiasEncaminhamento;
	}

	public Integer getQtdDiasResposta() {
		return qtdDiasResposta;
	}

	public void setQtdDiasResposta(Integer qtdDiasResposta) {
		this.qtdDiasResposta = qtdDiasResposta;
	}

	public Integer getQtdDiasAlertaMarco() {
		return qtdDiasAlertaMarco;
	}

	public void setQtdDiasAlertaMarco(Integer qtdDiasAlertaMarco) {
		this.qtdDiasAlertaMarco = qtdDiasAlertaMarco;
	}

	public Integer getQtdDiasAlertaEncaminhamento() {
		return qtdDiasAlertaEncaminhamento;
	}

	public void setQtdDiasAlertaEncaminhamento(Integer qtdDiasAlertaEncaminhamento) {
		this.qtdDiasAlertaEncaminhamento = qtdDiasAlertaEncaminhamento;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public Boolean getManterSigilo() {
		return manterSigilo;
	}

	public void setManterSigilo(Boolean manterSigilo) {
		this.manterSigilo = manterSigilo;
	}

	public Integer getFormaAtendimento() {
		return formaAtendimento;
	}

	public void setFormaAtendimento(Integer formaAtendimento) {
		this.formaAtendimento = formaAtendimento;
	}

	public Integer getPrioridadePadrao() {
		return prioridadePadrao;
	}

	public void setPrioridadePadrao(Integer prioridadePadrao) {
		this.prioridadePadrao = prioridadePadrao;
	}

	public Boolean getExibirDetalheEncaminhamento() {
		return exibirDetalheEncaminhamento;
	}

	public void setExibirDetalheEncaminhamento(Boolean exibirDetalheEncaminhamento) {
		this.exibirDetalheEncaminhamento = exibirDetalheEncaminhamento;
	}

	public Boolean getEnviarRespostaParcial() {
		return enviarRespostaParcial;
	}

	public void setEnviarRespostaParcial(Boolean enviarRespostaParcial) {
		this.enviarRespostaParcial = enviarRespostaParcial;
	}

	public Integer getRespostaParcialPadrao() {
		return respostaParcialPadrao;
	}

	public void setRespostaParcialPadrao(Integer respostaParcialPadrao) {
		this.respostaParcialPadrao = respostaParcialPadrao;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Configuracao other = (Configuracao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

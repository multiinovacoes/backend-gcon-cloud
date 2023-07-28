package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class ConfiguracaoDto {
	
	@ApiModelProperty(value = "Id da configuração")
	private Long id;

	@ApiModelProperty(value = "Qtd dias vencer atendimento")
	private Integer qtdDiasVencer;
	
	@ApiModelProperty(value = "Qtd dias desvios atendimento")
	private Integer qtdDiasDesvioAtendimento;
	
	@ApiModelProperty(value = "Qtd dias desvio encaminhamento")
	private Integer qtdDiasDesvioEncaminhamento;
	
	@ApiModelProperty(value = "Qtd dias desvio marco atendimento")
	private Integer qtdDiasDesvioMarco;
	
	@ApiModelProperty(value = "Qtd dias encaminhamento")
	private Integer qtdDiasEncaminhamento;
	
	@ApiModelProperty(value = "Qtd dias resposta")
	private Integer qtdDiasResposta;
	
	@ApiModelProperty(value = "Qtd dias alerta atendimento")
	private Integer qtdDiasAlertaMarco;
	
	@ApiModelProperty(value = "Qtd dias alerta encaminhamento")
	private Integer qtdDiasAlertaEncaminhamento;
	
	@ApiModelProperty(value = "Qtd dias exibir atendimentos enviados")
	private Integer qtdDiasExibirAtendimentosEnviados;
	
	@ApiModelProperty(value = "Qtd intervalo dias email")
	private Integer qtdIntervaloDiasEmail;
	
	@ApiModelProperty(value = "Orgão da configuração")
	private Long orgao;
	
	@ApiModelProperty(value = "Manter sigilo atendimento")
	private Boolean manterSigilo;
	
	@ApiModelProperty(value = "Forma de atendimento")
	private Integer formaAtendimento;
	
	@ApiModelProperty(value = "Prioridade padrão do atendimento")
	private Integer prioridadePadrao;
	
	@ApiModelProperty(value = "Exibir detalhe encaminhamento")
	private Boolean exibirDetalheEncaminhamento;
	
	@ApiModelProperty(value = "Enviar resposta parcial encaminhamento")
	private Boolean enviarRespostaParcial;
	
	@ApiModelProperty(value = "Resposta parcial padrão")
	private Integer respostaParcialPadrao;
	
	@ApiModelProperty(value = "Numero dias prorroga atendimento")
	private Integer numeroDiasProrrogarAtendimento;
	
	@ApiModelProperty(value = "Resposta parcial padrão prorrogação")
	private Integer respostaParcialPadraoProrrogacao;

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

	public Integer getNumeroDiasProrrogarAtendimento() {
		return numeroDiasProrrogarAtendimento;
	}

	public void setNumeroDiasProrrogarAtendimento(Integer numeroDiasProrrogarAtendimento) {
		this.numeroDiasProrrogarAtendimento = numeroDiasProrrogarAtendimento;
	}

	public Integer getRespostaParcialPadraoProrrogacao() {
		return respostaParcialPadraoProrrogacao;
	}

	public void setRespostaParcialPadraoProrrogacao(Integer respostaParcialPadraoProrrogacao) {
		this.respostaParcialPadraoProrrogacao = respostaParcialPadraoProrrogacao;
	}
	
	


}

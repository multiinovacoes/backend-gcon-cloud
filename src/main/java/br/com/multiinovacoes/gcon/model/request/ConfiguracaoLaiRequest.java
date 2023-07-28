package br.com.multiinovacoes.gcon.model.request;

public class ConfiguracaoLaiRequest {
	
	private Long id;
	
	private Long orgao;

	private Integer qtdDiasVencer;
	
	private Integer qtdDiasEncaminhamento;
	
	private Integer qtdDiasAlertaEncaminhamento;
	
	private Long modeloDocumentoDespacho;
	
	private Integer qtdMaxProrrogaPrazoAtendimento;
	
	private Integer qtdDiasPermitirAbrirRecurso;

	private Integer qtdDiasVencerRecurso;

	private Integer qtdDiasAlertaRecurso;

	private Boolean disparaEmailCobrancaAtendimento;

	private Boolean disparaEmailCobrancaRecurso;
	
	public ConfiguracaoLaiRequest() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public Integer getQtdDiasVencer() {
		return qtdDiasVencer;
	}

	public void setQtdDiasVencer(Integer qtdDiasVencer) {
		this.qtdDiasVencer = qtdDiasVencer;
	}

	public Integer getQtdDiasEncaminhamento() {
		return qtdDiasEncaminhamento;
	}

	public void setQtdDiasEncaminhamento(Integer qtdDiasEncaminhamento) {
		this.qtdDiasEncaminhamento = qtdDiasEncaminhamento;
	}

	public Integer getQtdDiasAlertaEncaminhamento() {
		return qtdDiasAlertaEncaminhamento;
	}

	public void setQtdDiasAlertaEncaminhamento(Integer qtdDiasAlertaEncaminhamento) {
		this.qtdDiasAlertaEncaminhamento = qtdDiasAlertaEncaminhamento;
	}

	public Long getModeloDocumentoDespacho() {
		return modeloDocumentoDespacho;
	}

	public void setModeloDocumentoDespacho(Long modeloDocumentoDespacho) {
		this.modeloDocumentoDespacho = modeloDocumentoDespacho;
	}

	public Integer getQtdMaxProrrogaPrazoAtendimento() {
		return qtdMaxProrrogaPrazoAtendimento;
	}

	public void setQtdMaxProrrogaPrazoAtendimento(Integer qtdMaxProrrogaPrazoAtendimento) {
		this.qtdMaxProrrogaPrazoAtendimento = qtdMaxProrrogaPrazoAtendimento;
	}

	public Integer getQtdDiasPermitirAbrirRecurso() {
		return qtdDiasPermitirAbrirRecurso;
	}

	public void setQtdDiasPermitirAbrirRecurso(Integer qtdDiasPermitirAbrirRecurso) {
		this.qtdDiasPermitirAbrirRecurso = qtdDiasPermitirAbrirRecurso;
	}

	public Integer getQtdDiasVencerRecurso() {
		return qtdDiasVencerRecurso;
	}

	public void setQtdDiasVencerRecurso(Integer qtdDiasVencerRecurso) {
		this.qtdDiasVencerRecurso = qtdDiasVencerRecurso;
	}

	public Integer getQtdDiasAlertaRecurso() {
		return qtdDiasAlertaRecurso;
	}

	public void setQtdDiasAlertaRecurso(Integer qtdDiasAlertaRecurso) {
		this.qtdDiasAlertaRecurso = qtdDiasAlertaRecurso;
	}

	public Boolean getDisparaEmailCobrancaAtendimento() {
		return disparaEmailCobrancaAtendimento;
	}

	public void setDisparaEmailCobrancaAtendimento(Boolean disparaEmailCobrancaAtendimento) {
		this.disparaEmailCobrancaAtendimento = disparaEmailCobrancaAtendimento;
	}

	public Boolean getDisparaEmailCobrancaRecurso() {
		return disparaEmailCobrancaRecurso;
	}

	public void setDisparaEmailCobrancaRecurso(Boolean disparaEmailCobrancaRecurso) {
		this.disparaEmailCobrancaRecurso = disparaEmailCobrancaRecurso;
	}
	
	


}

package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "LAI_CONFIGURACOES")
public class ConfiguracaoLai implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "INCODIGO")
	private Long id;
	
	@Column(name = "INCODIGOORGAO")
	private Long orgao;

	@Column(name = "INQTDDIASVENCERATENDIMENTO")
	private Integer qtdDiasVencer;
	
	@Column(name = "INQTDDIASRESPONDERENCAMINHAMENTO")
	private Integer qtdDiasEncaminhamento;
	
	@Column(name = "INQTDDIASALERTAENCAMINHAMENTO")
	private Integer qtdDiasAlertaEncaminhamento;
	
	@Column(name = "SM_MODELO_DOCUMENTO_DESPACHO")
	private Long modeloDocumentoDespacho;
	
	@Column(name = "IN_QTD_MAX_PRORROGA_PRAZO_ATENDIMENTO")
	private Integer qtdMaxProrrogaPrazoAtendimento;
	
	@Column(name = "INDIASPERMISSAOABRIRRECURSO")
	private Integer qtdDiasPermitirAbrirRecurso;

	@Column(name = "INQTDDIASVENCERRECURSO")
	private Integer qtdDiasVencerRecurso;

	@Column(name = "INQTDDIASALERTARECURSO")
	private Integer qtdDiasAlertaRecurso;

	@Column(name = "DISPARA_EMAIL_COBRANCA_ENCAMINHAMENTO")
	private Boolean disparaEmailCobrancaAtendimento;

	@Column(name = "DISPARA_EMAIL_COBRANCA_RECURSO")
	private Boolean disparaEmailCobrancaRecurso;
	
	
	
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
		ConfiguracaoLai other = (ConfiguracaoLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

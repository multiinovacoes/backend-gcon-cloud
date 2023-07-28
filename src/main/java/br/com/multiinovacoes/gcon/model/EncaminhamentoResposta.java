package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.multiinovacoes.gcon.util.RegexHtml;


@Entity
@Table(name = "OUVIDORIA_ENCAMINHAMENTO_RESPOSTA")
public class EncaminhamentoResposta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	public static final Integer STATUS_RETORNO = 0;
	public static final Integer STATUS_RETORNO_MANUAL = 1;
	public static final Integer STATUS_CANCELADO = 2;
	
	@Id
	@Column(name = "INCODIGORESPOSTA")
	private Long id;
	
	
	@Column(name = "INCODIGOENCAMINHAMENTO")
	private Long encaminhamento;
	
	@Column(name = "INCODIGOMODELODOCUMENTO")
	private Long modeloDocumento;

	@Column(name = "VADESPACHO")
	private String despacho;

	@Column(name = "DARESPOSTA")
	private LocalDate dataResposta;
	
	@Column(name = "INSATISFAZ")
	private Integer satisfaz;
	
	@Column(name = "SMSTATUS")
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescricaoDespacho() {
		return RegexHtml.getMatherEncaminhamentoResposta(despacho);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getModeloDocumento() {
		return modeloDocumento;
	}

	public void setModeloDocumento(Long modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public LocalDate getDataResposta() {
		return dataResposta;
	}

	public void setDataResposta(LocalDate dataResposta) {
		this.dataResposta = dataResposta;
	}

	public Long getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(Long encaminhamento) {
		this.encaminhamento = encaminhamento;
	}

	public Integer getSatisfaz() {
		return satisfaz;
	}

	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
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
		EncaminhamentoResposta other = (EncaminhamentoResposta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

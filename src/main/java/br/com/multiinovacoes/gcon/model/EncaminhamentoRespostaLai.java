package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.multiinovacoes.gcon.util.RegexHtml;


@Entity
@Table(name = "LAI_ENCAMINHAMENTO_RESPOSTA")
public class EncaminhamentoRespostaLai implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	public static final Integer STATUS_RETORNO = 0;
	public static final Integer STATUS_RETORNO_MANUAL = 1;
	public static final Integer STATUS_CANCELADO = 2;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_ENCAMINHAMENTO_RESPOSTA")
	private Long id;
	
	@Column(name = "CODIGO_ENCAMINHAMENTO")
	private Long encaminhamento;
	
	@Column(name = "CODIGO_ORIGEM")
	private Long setorOrigem;

	@Column(name = "CODIGO_DESTINO")
	private Long setorDestino;
	
	@Column(name = "CODIGO_MODELO")
	private Long modeloDocumento;

	@Column(name = "DESPACHO")
	private String despacho;

	@Column(name = "DATA")
	private LocalDateTime dataResposta;
	
	@Column(name = "SATISFAZ")
	private Integer satisfaz;
	
	@Column(name = "STATUS")
	private Integer status;
	

	public Long getSetorOrigem() {
		return setorOrigem;
	}

	public void setSetorOrigem(Long setorOrigem) {
		this.setorOrigem = setorOrigem;
	}

	public Long getSetorDestino() {
		return setorDestino;
	}

	public void setSetorDestino(Long setorDestino) {
		this.setorDestino = setorDestino;
	}

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

	public LocalDateTime getDataResposta() {
		return dataResposta;
	}

	public void setDataResposta(LocalDateTime dataResposta) {
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
		EncaminhamentoRespostaLai other = (EncaminhamentoRespostaLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

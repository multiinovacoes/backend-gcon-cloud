package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "LAI_DESPACHO")  
public class DespachoLai implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INCODIGO_DESPACHO")
	private Long id;
	
	@Column(name = "INATENDIMENTOID")
	private Long atendimento;
	
	@Column(name = "INCODIGO_SETOR_ORIGEM")
	private Long setorOrigem;
	
	@Column(name = "INCODIGO_SETOR_DESTINO")
	private Long setorDestino;
	
	@Column(name = "INCODIGO_MODELO")
	private Long modeloDocumento;

	@Column(name = "VADESPACHO")
	private String descricao;

	@Column(name = "DADESPACHO")
	private LocalDateTime dataDespacho;

	@Column(name = "VAEMAILENVIADO")
	private String emailEnviado;

	@Column(name = "INCODIGOUSUARIO")
	private Long usuario;

	@Column(name = "INSTATUS")
	private Integer status;
	
	@Column(name = "INCODIGOENCAMINHAMENTOENVIO")
	private Long codigoEncaminhamento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public Long getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}


	public Long getSetorDestino() {
		return setorDestino;
	}

	public void setSetorDestino(Long setorDestino) {
		this.setorDestino = setorDestino;
	}

	public Long getModeloDocumento() {
		return modeloDocumento;
	}

	public void setModeloDocumento(Long modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}


	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
	}

	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}


	
	public LocalDateTime getDataDespacho() {
		return dataDespacho;
	}


	public void setDataDespacho(LocalDateTime dataDespacho) {
		this.dataDespacho = dataDespacho;
	}

	public Long getSetorOrigem() {
		return setorOrigem;
	}

	public void setSetorOrigem(Long setorOrigem) {
		this.setorOrigem = setorOrigem;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getCodigoEncaminhamento() {
		return codigoEncaminhamento;
	}

	public void setCodigoEncaminhamento(Long codigoEncaminhamento) {
		this.codigoEncaminhamento = codigoEncaminhamento;
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
		DespachoLai other = (DespachoLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "OUVIDORIA_DESPACHO")  
public class Despacho implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "INCODIGO_DESPACHO")
	private Long id;
	
	@Column(name = "INCODIGO_ATENDIMENTO")
	private Long codigoAtendimento;
	
	@Column(name = "INCODIGO_ORGAO_ORIGEM")
	private Long orgaoOrigem;
	
	@Column(name = "INCODIGO_ORGAO_DESTINO")
	private Long orgaoDestino;
	
	@Column(name = "INCODIGO_SETOR_ORIGEM")
	private Long setorOrigem;
	
	@Column(name = "INCODIGO_SETOR_DESTINO")
	private Long setorDestino;
	
	@Column(name = "INCODIGO_MODELO")
	private Long modeloDocumento;

	@Column(name = "VADESPACHO")
	private String descricao;

	@Column(name = "DADESPACHO")
	private LocalDate dataDespacho;

	@Column(name = "VAEMAILENVIADO")
	private String emailEnviado;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "INCODIGOUSUARIO")
	private Usuario usuario;

	@Column(name = "INSTATUS")
	private Integer status;
	
	@Column(name = "INCODIGOENCAMINHAMENTOENVIO")
	private Long codigoEncaminhamento;
	
	@Column(name = "SMANOATENDIMENTO")
	private Integer anoAtendimento;
	
	@Column(name = "INATENDIMENTOID")
	private Long atendimento;
	
	@Transient
	private String descricaoSetorOrigem;

	@Transient
	private String descricaoSetorDestino;
	
	@Transient
	private String dataFormatada;

	public String getDataFormatada() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dataFormatada = getDataDespacho().format(formatter);
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada) {
		this.dataFormatada = dataFormatada;
	}

	public String getDescricaoSetorOrigem() {
		return descricaoSetorOrigem;
	}

	public void setDescricaoSetorOrigem(String descricaoSetorOrigem) {
		this.descricaoSetorOrigem = descricaoSetorOrigem;
	}

	public String getDescricaoSetorDestino() {
		return descricaoSetorDestino;
	}

	public void setDescricaoSetorDestino(String descricaoSetorDestino) {
		this.descricaoSetorDestino = descricaoSetorDestino;
	}


	public Long getCodigoAtendimento() {
		return codigoAtendimento;
	}

	public void setCodigoAtendimento(Long codigoAtendimento) {
		this.codigoAtendimento = codigoAtendimento;
	}

	public Integer getAnoAtendimento() {
		return anoAtendimento;
	}

	public void setAnoAtendimento(Integer anoAtendimento) {
		this.anoAtendimento = anoAtendimento;
	}

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

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	
	public LocalDate getDataDespacho() {
		return dataDespacho;
	}


	public void setDataDespacho(LocalDate dataDespacho) {
		this.dataDespacho = dataDespacho;
	}



	public Long getOrgaoOrigem() {
		return orgaoOrigem;
	}

	public void setOrgaoOrigem(Long orgaoOrigem) {
		this.orgaoOrigem = orgaoOrigem;
	}

	public Long getOrgaoDestino() {
		return orgaoDestino;
	}

	public void setOrgaoDestino(Long orgaoDestino) {
		this.orgaoDestino = orgaoDestino;
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
		Despacho other = (Despacho) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

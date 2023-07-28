package br.com.multiinovacoes.gcon.model;


import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "OUVIDORIA_ENCAMINHAMENTO_ENVIO")
public class Encaminhamento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	
	public static final int STATUS_ABERTO = 0;
	public static final int STATUS_RESPONDIDO = 1;
	
	public static final int CANCELADO_NAO = 0;
	public static final int CANCELADO_SIM = 1;

	@Id
	@Column(name = "INCODIGOENCAMINHAMENTOENVIO")
	private Long id;
	
	@Column(name = "INATENDIMENTOID")
	private Long atendimento;
	
	@Column(name = "INCODIGOORGAOORIGEM")
	private Long orgaoOrigem;
	
	@Column(name = "INCODIGOORGAODESTINO")
	private Long orgaoDestino;
	
	@Column(name = "INCODIGOSETORORIGEM")
	private Long setorOrigem;
	
	@Column(name = "INCODIGOSETORDESTINO")
	private Long setorDestino;
	
	@Column(name = "INCODIGOMODELO")
	private Long modeloDocumento;

	@Column(name = "VADESPACHO")
	private String despacho;

	@Column(name = "DAENCAMINHAMENTO")
	private LocalDate dataEncaminhamento;

	@Column(name = "VAEMAILENVIADO")
	private String emailEnviado;

	@ManyToOne(fetch = FetchType.LAZY) 
	@JoinColumn(name = "INCODIGOUSUARIO")
	private Usuario usuario;

	@Column(name = "INCOPIADIRIGENTE")
	private Integer copiaDirigente;
	
	@Column(name = "DAPRAZOENCAMINHAMENTO")
	private LocalDate dataPrazo;
	
	@Column(name = "INSTATUS")
	private Integer status;

	@Column(name = "SMCANCELADO")
	private Integer cancelado;

	@Column(name = "INRECEBIDO")
	private Integer recebeu;
	
	@Transient
	private Integer sequencial;
	
	@Column(name = "SMANEXOENVIADO")
	private Integer anexoEnviado;
	
	@Transient
	private String descricaoSetorOrigem;

	@Transient
	private String descricaoSetorDestino;
	
	@Transient
	private String dataEncaminhado;
	
	@Column(name = "PARAM_CRIPTOGRAFADO")
	private String parametro;


	public String getDataEncaminhado() {
		return dataEncaminhado;
	}

	public void setDataEncaminhado(String dataEncaminhado) {
		this.dataEncaminhado = dataEncaminhado;
	}

	public void setDescricaoSetorOrigem(String descricaoSetorOrigem) {
		this.descricaoSetorOrigem = descricaoSetorOrigem;
	}

	public void setDescricaoSetorDestino(String descricaoSetorDestino) {
		this.descricaoSetorDestino = descricaoSetorDestino;
	}

	public Integer getAnexoEnviado() {
		return anexoEnviado;
	}

	public String getDescricaoSetorOrigem() {
		return descricaoSetorOrigem;
	}

	public String getDescricaoSetorDestino() {
		return descricaoSetorDestino;
	}

	public void setAnexoEnviado(Integer anexoEnviado) {
		this.anexoEnviado = anexoEnviado;
	}


	public Integer getSequencial() {
		return sequencial;
	}


	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}


	public Long getAtendimento() {
		return atendimento;
	}


	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public LocalDate getDataEncaminhamento() {
		return dataEncaminhamento;
	}

	public void setDataEncaminhamento(LocalDate dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
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


	public LocalDate getDataPrazo() {
		return dataPrazo;
	}

	public void setDataPrazo(LocalDate dataPrazo) {
		this.dataPrazo = dataPrazo;
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


	public Integer getCopiaDirigente() {
		return copiaDirigente;
	}


	public void setCopiaDirigente(Integer copiaDirigente) {
		this.copiaDirigente = copiaDirigente;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
	}


	public Integer getCancelado() {
		return cancelado;
	}


	public void setCancelado(Integer cancelado) {
		this.cancelado = cancelado;
	}


	public Integer getRecebeu() {
		return recebeu;
	}


	public void setRecebeu(Integer recebeu) {
		this.recebeu = recebeu;
	}


	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
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
		Encaminhamento other = (Encaminhamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

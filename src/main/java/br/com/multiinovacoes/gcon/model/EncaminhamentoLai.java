package br.com.multiinovacoes.gcon.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "LAI_ENCAMINHAMENTO")
public class EncaminhamentoLai {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_ENCAMINHAMENTO")
	private Long id;

	@Column(name = "CODIGO_ATENDIMENTO_LAI")
	private Long atendimento;

	@Column(name = "CODIGO_ORIGEM")
	private Long setorOrigem;

	@Column(name = "CODIGO_DESTINO")
	private Long setorDestino;

	@Column(name = "DATA")
	private LocalDateTime dataEncaminhamento;

	@Column(name = "DESPACHO")
	private String despacho;
	
	@Column(name = "RESPOSTA")
	private String resposta;
	
	@Column(name = "EMAIL_ENVIADO")
	private String emailEnviado;

	@Column(name = "CODIGO_MODELO")
	private Long modeloDocumento;

	@Column(name = "RECEBIDO")
	private Integer recebido;

	@Column(name = "SATISFAZ")
	private Integer satisfaz;

	@Column(name = "CODIGO_USUARIO")
	private Long usuario;

	@Column(name = "STATUS")
	private  Integer status;
	
	@Transient
	private String descricaoSetorOrigem;

	@Transient
	private String descricaoSetorDestino;
	
	@Transient
	private String dataEncaminhado;
	
	@Column(name = "PARAM_CRIPTOGRAFADO")
	private String parametro;

	@Column(name = "DATA_PRAZO_RESPOSTA")
	private LocalDate dataPrazoResposta;

	@Column(name = "QTD_COBRANCAS_EMAIL")
	private Integer qtdCobrancasEmail;
	
	
	
	public Integer getQtdCobrancasEmail() {
		return qtdCobrancasEmail;
	}

	public void setQtdCobrancasEmail(Integer qtdCobrancasEmail) {
		this.qtdCobrancasEmail = qtdCobrancasEmail;
	}

	@Transient
	private Integer sequencial;

	
	public EncaminhamentoLai() {
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

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
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

	public LocalDateTime getDataEncaminhamento() {
		return dataEncaminhamento;
	}

	public void setDataEncaminhamento(LocalDateTime dataEncaminhamento) {
		this.dataEncaminhamento = dataEncaminhamento;
	}

	public String getDespacho() {
		return despacho;
	}

	public void setDespacho(String despacho) {
		this.despacho = despacho;
	}

	public String getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
	}


	public Long getModeloDocumento() {
		return modeloDocumento;
	}

	public void setModeloDocumento(Long modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}

	public Integer getRecebido() {
		return recebido;
	}

	public void setRecebido(Integer recebido) {
		this.recebido = recebido;
	}

	public Integer getSatisfaz() {
		return satisfaz;
	}

	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
	}


	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public String getDataEncaminhado() {
		return dataEncaminhado;
	}

	public void setDataEncaminhado(String dataEncaminhado) {
		this.dataEncaminhado = dataEncaminhado;
	}

	public String getParametro() {
		return parametro;
	}

	public void setParametro(String parametro) {
		this.parametro = parametro;
	}

	public LocalDate getDataPrazoResposta() {
		return dataPrazoResposta;
	}

	public void setDataPrazoResposta(LocalDate dataPrazoResposta) {
		this.dataPrazoResposta = dataPrazoResposta;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
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
		EncaminhamentoLai other = (EncaminhamentoLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}

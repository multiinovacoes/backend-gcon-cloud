package br.com.multiinovacoes.gcon.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;


public class EncaminhamentoLaiDto {
	
	
	private Long id;

	private Long atendimento;

	private Long setorOrigem;

	private Long setorDestino;

	private LocalDateTime dataEncaminhamento;

	private String despacho;
	
	private String resposta;
	
	private String emailEnviado;

	private Long modeloDocumento;

	private Integer recebido;

	private Integer satisfaz;

	private Long usuario;

	private  Integer status;
	
	@ApiModelProperty(value = "Id resposta do encaminhamento")
	private Long idResposta;

	
	@ApiModelProperty(value = "Descrição do setor de origem")
	private String descricaoSetorOrigem;

	@ApiModelProperty(value = "Descrição do setor de destino")
	private String descricaoSetorDestino;

	@ApiModelProperty(value = "Data que foi encaminhado")
	private String dataEncaminhado;

	private LocalDate dataPrazoResposta;
	
	private Integer sequencial;
	
	private String tipo;
	
	private Integer qtdCobrancaEmail;
			

	public Integer getQtdCobrancaEmail() {
		return qtdCobrancaEmail;
	}

	public void setQtdCobrancaEmail(Integer qtdCobrancaEmail) {
		this.qtdCobrancaEmail = qtdCobrancaEmail;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	public Integer getSequencial() {
		return sequencial;
	}

	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}

	public LocalDate getDataPrazoResposta() {
		return dataPrazoResposta;
	}

	public void setDataPrazoResposta(LocalDate dataPrazoResposta) {
		this.dataPrazoResposta = dataPrazoResposta;
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
		EncaminhamentoLaiDto other = (EncaminhamentoLaiDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}

package br.com.multiinovacoes.gcon.model.dto;


import java.io.Serializable;
import java.time.LocalDate;

import br.com.multiinovacoes.gcon.model.Usuario;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class EncaminhamentoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	

	@ApiModelProperty(value = "Id do encaminhamento")
	private Long id;
	
	@ApiModelProperty(value = "Id do atendimento do encaminhamento")
	private Long atendimento;
	
	@ApiModelProperty(value = "Orgão de origem do encaminhamento")
	private Long orgaoOrigem;
	
	@ApiModelProperty(value = "Orgão destino do encaminhamento")
	private Long orgaoDestino;
	
	@ApiModelProperty(value = "Setor origem do encaminhamento")
	private Long setorOrigem;
	
	@ApiModelProperty(value = "Setor destino do encaminhamento")
	private Long setorDestino;
	
	@ApiModelProperty(value = "Modelo documento do encaminhamento")
	private Long modeloDocumento;

	@ApiModelProperty(value = "Despacho do encaminhamento")
	private String despacho;

	@ApiModelProperty(value = "Data do encaminhamento")
	private LocalDate dataEncaminhamento;

	@ApiModelProperty(value = "Email enviado encaminhamento")
	private String emailEnviado;

	@ApiModelProperty(value = "Usuario do encaminhamento")
	private Usuario usuario;

	@ApiModelProperty(value = "Copia dirigente do encaminhamento")
	private Integer copiaDirigente;
	
	@ApiModelProperty(value = "Data prazo do encaminhamento")
	private LocalDate dataPrazo;
	
	@ApiModelProperty(value = "Dias uteis do encaminhamento")
	private Integer diasUteisResposta;
	
	@ApiModelProperty(value = "Status do encaminhamento")
	private Integer status;

	@ApiModelProperty(value = "Token contendo o link para resposta do encaminhamento")
	private String tokenSetor;
	
	@ApiModelProperty(value = "Id resposta do encaminhamento")
	private Long idResposta;
	
	@ApiModelProperty(value = "Satisfaz a resposta do encaminhamento")
	private Integer satisfaz;
	
	@ApiModelProperty(value = "Tipo de encaminhamento")
	private String tipo;

	@ApiModelProperty(value = "Sequencial do encaminhamento no grid")
	private Integer sequencial;
	
	@ApiModelProperty(value = "Indica se o encaminhamento foi cancelado")
	private Integer cancelado;
	
	@ApiModelProperty(value = "Indica se o encaminhamento foi cancelado")
	private Integer recebeu;

	@ApiModelProperty(value = "Descrição do setor de origem")
	private String descricaoSetorOrigem;

	@ApiModelProperty(value = "Descrição do setor de destino")
	private String descricaoSetorDestino;

	@ApiModelProperty(value = "Data que foi encaminhado")
	private String dataEncaminhado;
	
	@ApiModelProperty(value = "Enviar resposta parcial do encaminhado")
	private boolean enviarRespostaParcial;
	
	@ApiModelProperty(value = "Parametro que identifica o encaminhamento")
	private String parametro;
	
	private Integer anexoEnviado;

	public Integer getAnexoEnviado() {
		return anexoEnviado;
	}


	public void setAnexoEnviado(Integer anexoEnviado) {
		this.anexoEnviado = anexoEnviado;
	}


	public String getParametro() {
		return parametro;
	}


	public void setParametro(String parametro) {
		this.parametro = parametro;
	}


	public boolean isEnviarRespostaParcial() {
		return enviarRespostaParcial;
	}


	public void setEnviarRespostaParcial(boolean enviarRespostaParcial) {
		this.enviarRespostaParcial = enviarRespostaParcial;
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


	public Integer getRecebeu() {
		return recebeu;
	}


	public void setRecebeu(Integer recebeu) {
		this.recebeu = recebeu;
	}


	public Integer getCancelado() {
		return cancelado;
	}


	public void setCancelado(Integer cancelado) {
		this.cancelado = cancelado;
	}


	public Integer getSequencial() {
		return sequencial;
	}


	public void setSequencial(Integer sequencial) {
		this.sequencial = sequencial;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Integer getSatisfaz() {
		return satisfaz;
	}


	public void setSatisfaz(Integer satisfaz) {
		this.satisfaz = satisfaz;
	}


	public Long getIdResposta() {
		return idResposta;
	}


	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}


	public String getTokenSetor() {
		return tokenSetor;
	}


	public void setTokenSetor(String tokenSetor) {
		this.tokenSetor = tokenSetor;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Integer getCopiaDirigente() {
		return copiaDirigente;
	}

	public void setCopiaDirigente(Integer copiaDirigente) {
		this.copiaDirigente = copiaDirigente;
	}

	public LocalDate getDataPrazo() {
		return dataPrazo;
	}

	public void setDataPrazo(LocalDate dataPrazo) {
		this.dataPrazo = dataPrazo;
	}

	public Integer getDiasUteisResposta() {
		return diasUteisResposta;
	}

	public void setDiasUteisResposta(Integer diasUteisResposta) {
		this.diasUteisResposta = diasUteisResposta;
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
		EncaminhamentoDto other = (EncaminhamentoDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;


public class EncaminhamentoRespostaLaiDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	public static final Integer STATUS_RETORNO = 0;
	public static final Integer STATUS_RETORNO_MANUAL = 1;
	
	@ApiModelProperty(value = "Id do encaminhamento resposta")
	private Long id;
	
	@ApiModelProperty(value = "Id do encaminhamento")
	private Long encaminhamento;
	
	@ApiModelProperty(value = "Id do modelo de encaminhamento")
	private Long modeloDocumento;

	@ApiModelProperty(value = "Despacho do encaminhamento resposta")
	private String despacho;

	@ApiModelProperty(value = "Data da resposta do encaminhamento")
	private LocalDateTime dataResposta;
	
	@ApiModelProperty(value = "Satisfaz do encaminhamento")
	private Integer satisfaz;
	
	@ApiModelProperty(value = "Status da resposta do encaminhamento")
	private Integer status;
	
	private String token;
	
	private Long setorOrigem;

	private Long setorDestino;

    private String descricaoSetorOrigem;

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


	public String getDescricaoSetorOrigem() {
		return descricaoSetorOrigem;
	}


	public void setDescricaoSetorOrigem(String descricaoSetorOrigem) {
		this.descricaoSetorOrigem = descricaoSetorOrigem;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public Integer getStatus() {
		return status;
	}


	public void setStatus(Integer status) {
		this.status = status;
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
		EncaminhamentoRespostaLaiDto other = (EncaminhamentoRespostaLaiDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

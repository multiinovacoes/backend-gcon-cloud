package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;
import java.time.LocalDate;

import br.com.multiinovacoes.gcon.model.Usuario;
import io.swagger.annotations.ApiModelProperty;


public class EncaminhamentoRespostaDto implements Serializable {

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
	private LocalDate dataResposta;
	
	@ApiModelProperty(value = "Satisfaz do encaminhamento")
	private Integer satisfaz;

	@ApiModelProperty(value = "Usuario do encaminhamento")
	private Usuario usuario;
	
	@ApiModelProperty(value = "Status da resposta do encaminhamento")
	private Integer status;
	
	private String token;

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



	public LocalDate getDataResposta() {
		return dataResposta;
	}


	public void setDataResposta(LocalDate dataResposta) {
		this.dataResposta = dataResposta;
	}


	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		EncaminhamentoRespostaDto other = (EncaminhamentoRespostaDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

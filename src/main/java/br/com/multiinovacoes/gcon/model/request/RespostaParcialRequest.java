package br.com.multiinovacoes.gcon.model.request;

import java.time.LocalDate;
import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.UsuarioDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RespostaParcialRequest {



	@ApiModelProperty(value = "Id da resposta parcial")	
	private Long id;
	
	@ApiModelProperty(value = "Id da origem manifestação")	
	private Long atendimento;
	
	@ApiModelProperty(value = "Modelo documento da resposta parcial")	
	private Long modeloDocumento;

	@ApiModelProperty(value = "Descrição da resposta parcial")	
	private String descricao;

	@ApiModelProperty(value = "Data Resposta da resposta parcial")	
	private LocalDate dataResposta;

	@ApiModelProperty(value = "Email da resposta parcial")	
	private String emailEnviado;

	@ApiModelProperty(value = "Usuario da resposta parcial")	
	private UsuarioDto usuario;
	
	@ApiModelProperty(value = "Forma envio da resposta parcial")	
	private Integer formaEnvio;

	@ApiModelProperty(value = "Status da resposta parcial")	
	private Integer status;
	
	@ApiModelProperty(value = "Órgão da resposta parcial")	
	private Long orgao;
	
	private List<Long>selectedAnexos;

	public List<Long> getSelectedAnexos() {
		return selectedAnexos;
	}

	public void setSelectedAnexos(List<Long> selectedAnexos) {
		this.selectedAnexos = selectedAnexos;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public Long getAtendimento() {
		return atendimento;
	}


	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
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

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public Integer getStatus() {
		return status;
	}
	
	

	public LocalDate getDataResposta() {
		return dataResposta;
	}


	public void setDataResposta(LocalDate dataResposta) {
		this.dataResposta = dataResposta;
	}


	public Integer getFormaEnvio() {
		return formaEnvio;
	}


	public void setFormaEnvio(Integer formaEnvio) {
		this.formaEnvio = formaEnvio;
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
		RespostaParcialRequest other = (RespostaParcialRequest) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}

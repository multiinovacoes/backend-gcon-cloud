package br.com.multiinovacoes.gcon.model.request;


import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.UsuarioDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;


@Api
public class AtendimentoConclusaoRequest {

	
	@ApiModelProperty(value = "id do atendimento")
	private Long atendimento;
	
	@ApiModelProperty(value = "Email do atendimento")
	private String email;
	
	@ApiModelProperty(value = "Usuário criação do atendimento")
	private UsuarioDto usuario;
	
	@ApiModelProperty(value = "Resposta do atendimento")
	private String textoProvidencia;
	
	@ApiModelProperty(value = "Forma resposta do atendimento")
	private Integer formaResposta;

	@ApiModelProperty(value = "Modelo de resposta do atendimento")
	private Long modeloDocumento;

	private List<Long>selectedAnexos;

	public List<Long> getSelectedAnexos() {
		return selectedAnexos;
	}

	public void setSelectedAnexos(List<Long> selectedAnexos) {
		this.selectedAnexos = selectedAnexos;
	}

	public Long getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Long atendimento) {
		this.atendimento = atendimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public String getTextoProvidencia() {
		return textoProvidencia;
	}

	public void setTextoProvidencia(String textoProvidencia) {
		this.textoProvidencia = textoProvidencia;
	}

	public Integer getFormaResposta() {
		return formaResposta;
	}

	public void setFormaResposta(Integer formaResposta) {
		this.formaResposta = formaResposta;
	}

	public Long getModeloDocumento() {
		return modeloDocumento;
	}

	public void setModeloDocumento(Long modeloDocumento) {
		this.modeloDocumento = modeloDocumento;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


	
	

}

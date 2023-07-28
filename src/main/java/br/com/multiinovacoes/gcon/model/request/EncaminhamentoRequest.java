package br.com.multiinovacoes.gcon.model.request;


import java.io.Serializable;
import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.UsuarioDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class EncaminhamentoRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	
	
	@ApiModelProperty(value = "Id do atendimento do encaminhamento")
	private Long atendimento;
	
	@ApiModelProperty(value = "Setor destino do encaminhamento")
	private Long setorDestino;
	
	@ApiModelProperty(value = "Modelo documento do encaminhamento")
	private Long modeloDocumento;

	@ApiModelProperty(value = "Despacho do encaminhamento")
	private String despacho;

	@ApiModelProperty(value = "Copia dirigente do encaminhamento")
	private Integer copiaDirigente;
	
	@ApiModelProperty(value = "Email do encaminhamento")
	private String emailEnviado;
	
	@ApiModelProperty(value = "Copia dirigente do encaminhamento")
	private Boolean copiaEmailDirigente;

	@ApiModelProperty(value = "Setor destino do encaminhamento")
	private Long setorOrigem;
	
	private boolean enviarRespostaParcial;
	
	private UsuarioDto usuario;
	
    private List<Long>selectedAnexos;
    
	private Integer anexoEnviado;

	public Integer getAnexoEnviado() {
		return anexoEnviado;
	}

	public void setAnexoEnviado(Integer anexoEnviado) {
		this.anexoEnviado = anexoEnviado;
	}

	public boolean isEnviarRespostaParcial() {
		return enviarRespostaParcial;
	}

	public void setEnviarRespostaParcial(boolean enviarRespostaParcial) {
		this.enviarRespostaParcial = enviarRespostaParcial;
	}

	public List<Long> getSelectedAnexos() {
		return selectedAnexos;
	}

	public void setSelectedAnexos(List<Long> selectedAnexos) {
		this.selectedAnexos = selectedAnexos;
	}

	public UsuarioDto getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDto usuario) {
		this.usuario = usuario;
	}

	public Long getSetorOrigem() {
		return setorOrigem;
	}

	public void setSetorOrigem(Long setorOrigem) {
		this.setorOrigem = setorOrigem;
	}

	public String getEmailEnviado() {
		return emailEnviado;
	}

	public void setEmailEnviado(String emailEnviado) {
		this.emailEnviado = emailEnviado;
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

	public Integer getCopiaDirigente() {
		return copiaDirigente;
	}

	public void setCopiaDirigente(Integer copiaDirigente) {
		this.copiaDirigente = copiaDirigente;
	}

	public Boolean getCopiaEmailDirigente() {
		return copiaEmailDirigente;
	}

	public void setCopiaEmailDirigente(Boolean copiaEmailDirigente) {
		this.copiaEmailDirigente = copiaEmailDirigente;
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

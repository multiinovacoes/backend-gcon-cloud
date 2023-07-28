package br.com.multiinovacoes.gcon.model.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("Atendimento Response")
public class AtendimentoConectaResponse {
	
	@ApiModelProperty(value = "Nome Solicitante")
	public String nomeSolicitante;
	@ApiModelProperty(value = "Numero Protocolo")
    public String numeroProtocolo;
	@ApiModelProperty(value = "Senha Manifestante")
    public String senhaManifestante;
	

	public String getNomeSolicitante() {
		return nomeSolicitante;
	}

	public void setNomeSolicitante(String nomeSolicitante) {
		this.nomeSolicitante = nomeSolicitante;
	}

	public String getNumeroProtocolo() {
		return numeroProtocolo;
	}

	public void setNumeroProtocolo(String numeroProtocolo) {
		this.numeroProtocolo = numeroProtocolo;
	}

	public String getSenhaManifestante() {
		return senhaManifestante;
	}

	public void setSenhaManifestante(String senhaManifestante) {
		this.senhaManifestante = senhaManifestante;
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

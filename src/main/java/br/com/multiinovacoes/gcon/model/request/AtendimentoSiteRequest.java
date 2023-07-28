package br.com.multiinovacoes.gcon.model.request;


import java.util.List;

import br.com.multiinovacoes.gcon.model.dto.ListaAnexoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;


@Api
public class AtendimentoSiteRequest {
	
	private String identificado;
	
	

	
//	@ApiModelProperty(value = "id do atendimento")
//	private Long id;
//	
//	@ApiModelProperty(value = "Orgao do atendimento")
//	private Long orgao;
	
//	@ApiModelProperty(value = "Número do atendimento")
//	private Long numeroAtendimento;
//
//	@ApiModelProperty(value = "Número do protocolo")
//	private String numeroProtocolo;
//	
//	@ApiModelProperty(value = "Ano de atendimento")
//	private Integer anoAtendimento;
//	
//	@ApiModelProperty(value = "Manter sigilo do atendimento")
//	private Integer manterSigilo;
//	
//	@ApiModelProperty(value = "Tipo de usuário do atendimento")
//	private Long tipoUsuario;
//
//	@ApiModelProperty(value = "Nome solicitante do atendimento")
//	private String nomeSolicitante;
//	
//	@ApiModelProperty(value = "Email do atendimento")
//	private String email;
//	
//	@ApiModelProperty(value = "DDD celular do atendimento")
//	private String dddCelular;
//	
//	@ApiModelProperty(value = "Fone celular do atendimento")
//	private String foneCelular;
//	
//	@ApiModelProperty(value = "Descrição do atendimento")
//	private String descricao;
//	
	@ApiModelProperty(value = "Natureza do atendimento")
	private Long natureza;
//	
//	@ApiModelProperty(value = "Protocolo de origem do atendimento")
//	private String protocoloOrigem;
//	
//	@ApiModelProperty(value = "Senha de acesso do atendimento")
//	private String senhaManifestante;
//
//	@ApiModelProperty(value = "Identificação do atendimento")
//	private String identificado;
//	
//	@ApiModelProperty(value = "Sigilo do atendimento no site")
//	private Boolean sigilo;
//	
	private String logotipo;
//	
//	private String captcha;
//	
//	
//	
//	
//	
//	
//
//	public String getCaptcha() {
//		return captcha;
//	}
//
//	public void setCaptcha(String captcha) {
//		this.captcha = captcha;
//	}
//
	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}
//
//	public Boolean getSigilo() {
//		return sigilo;
//	}
//
//	public void setSigilo(Boolean sigilo) {
//		this.sigilo = sigilo;
//	}
//
	@ApiModelProperty(value = "Lista de anexos")
	private List<ListaAnexoDto> listaAnexoDto;
	
	
	
	public List<ListaAnexoDto> getListaAnexoDto() {
		return listaAnexoDto;
	}

	public void setListaAnexoDto(List<ListaAnexoDto> listaAnexoDto) {
		this.listaAnexoDto = listaAnexoDto;
	}
//
//	public Integer getAnoAtendimento() {
//		return anoAtendimento;
//	}
//
//	public void setAnoAtendimento(Integer anoAtendimento) {
//		this.anoAtendimento = anoAtendimento;
//	}
//
//
//	public String getSenhaManifestante() {
//		return senhaManifestante;
//	}
//
//	public void setSenhaManifestante(String senhaManifestante) {
//		this.senhaManifestante = senhaManifestante;
//	}
//
//	public Long getNumeroAtendimento() {
//		return numeroAtendimento;
//	}
//
//	public void setNumeroAtendimento(Long numeroAtendimento) {
//		this.numeroAtendimento = numeroAtendimento;
//	}
//
//	public String getNumeroProtocolo() {
//		return numeroProtocolo;
//	}
//
//	public void setNumeroProtocolo(String numeroProtocolo) {
//		this.numeroProtocolo = numeroProtocolo;
//	}
//
//	public Long getOrgao() {
//		return orgao;
//	}
//
//	public void setOrgao(Long orgao) {
//		this.orgao = orgao;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//	public String getProtocoloOrigem() {
//		return protocoloOrigem;
//	}
//
//	public void setProtocoloOrigem(String protocoloOrigem) {
//		this.protocoloOrigem = protocoloOrigem;
//	}
//
//	public Integer getManterSigilo() {
//		return manterSigilo;
//	}
//
//	public void setManterSigilo(Integer manterSigilo) {
//		this.manterSigilo = manterSigilo;
//	}
//
//	public Long getTipoUsuario() {
//		return tipoUsuario;
//	}
//
//	public void setTipoUsuario(Long tipoUsuario) {
//		this.tipoUsuario = tipoUsuario;
//	}
//
//	public String getNomeSolicitante() {
//		return nomeSolicitante;
//	}
//
//	public void setNomeSolicitante(String nomeSolicitante) {
//		this.nomeSolicitante = nomeSolicitante;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getDddCelular() {
//		return dddCelular;
//	}
//
//	public void setDddCelular(String dddCelular) {
//		this.dddCelular = dddCelular;
//	}
//
//	public String getFoneCelular() {
//		return foneCelular;
//	}
//
//	public void setFoneCelular(String foneCelular) {
//		this.foneCelular = foneCelular;
//	}
//
	public Long getNatureza() {
		return natureza;
	}

	public void setNatureza(Long natureza) {
		this.natureza = natureza;
	}

//	public String getDescricao() {
//		return descricao;
//	}
//
//	public void setDescricao(String descricao) {
//		this.descricao = descricao;
//	}
//
//	public String getIdentificado() {
//		return identificado;
//	}
//
//	public void setIdentificado(String identificado) {
//		this.identificado = identificado;
//	}

	
	public String getIdentificado() {
		return identificado;
	}



	public void setIdentificado(String identificado) {
		this.identificado = identificado;
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

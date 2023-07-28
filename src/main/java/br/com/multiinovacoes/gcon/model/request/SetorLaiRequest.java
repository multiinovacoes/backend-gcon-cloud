package br.com.multiinovacoes.gcon.model.request;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class SetorLaiRequest {
	
	@ApiModelProperty(value = "Id do setor")
	private Long id;

	@ApiModelProperty(value = "Descrição do setor")
	private String descricao;

	@ApiModelProperty(value = "Status do setor")
	private Integer status;

	@ApiModelProperty(value = "Órgão do setor")
	private Long orgao;

	@ApiModelProperty(value = "Sigla do setor")
	private String sigla;

	@ApiModelProperty(value = "Nome dirigente do setor")
	private String nomeDirigente;

	@ApiModelProperty(value = "Email diregente do setor")
	private String emailDirigente;

	@ApiModelProperty(value = "Email do setor")
	private String emailSetor;
	
	@ApiModelProperty(value = "Data Criação do setor")
	private LocalDateTime dataCriacao;
	
	private Integer tipoAcessoPerfil;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getNomeDirigente() {
		return nomeDirigente;
	}

	public void setNomeDirigente(String nomeDirigente) {
		this.nomeDirigente = nomeDirigente;
	}

	public String getEmailDirigente() {
		return emailDirigente;
	}

	public void setEmailDirigente(String emailDirigente) {
		this.emailDirigente = emailDirigente;
	}

	public String getEmailSetor() {
		return emailSetor;
	}

	public void setEmailSetor(String emailSetor) {
		this.emailSetor = emailSetor;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public Integer getTipoAcessoPerfil() {
		return tipoAcessoPerfil;
	}

	public void setTipoAcessoPerfil(Integer tipoAcessoPerfil) {
		this.tipoAcessoPerfil = tipoAcessoPerfil;
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

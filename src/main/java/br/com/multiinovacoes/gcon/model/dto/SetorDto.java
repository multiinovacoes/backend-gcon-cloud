package br.com.multiinovacoes.gcon.model.dto;

import java.time.LocalDateTime;

import io.swagger.annotations.ApiModelProperty;

public class SetorDto {
	
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

	@ApiModelProperty(value = "Fone do setor")
	private String fone;

	@ApiModelProperty(value = "Nome dirigente do setor")
	private String nomeDirigente;

	@ApiModelProperty(value = "Email diregente do setor")
	private String emailDirigente;

	@ApiModelProperty(value = "Tratamento formal do setor")
	private String tratamentoFormal;

	@ApiModelProperty(value = "Email do setor")
	private String emailSetor;

	@ApiModelProperty(value = "Gênero dirigente do setor")
	private String generoDirigente;

	@ApiModelProperty(value = "Gênero do setor")
	private String generoSetor;

	@ApiModelProperty(value = "Cargo dirigente do setor")
	private String cargoDirigente;
	
	@ApiModelProperty(value = "Nome suplente do setor")
	private String nomeSuplente;
	
	@ApiModelProperty(value = "Email suplente do setor")
	private String emailSuplente;

	@ApiModelProperty(value = "Responsável do setor")
	private String responsavelSetor;
	
	@ApiModelProperty(value = "Data Criação do setor")
	private LocalDateTime dataCriacao;
	

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getNomeSuplente() {
		return nomeSuplente;
	}

	public void setNomeSuplente(String nomeSuplente) {
		this.nomeSuplente = nomeSuplente;
	}

	public String getEmailSuplente() {
		return emailSuplente;
	}

	public void setEmailSuplente(String emailSuplente) {
		this.emailSuplente = emailSuplente;
	}

	public String getResponsavelSetor() {
		return responsavelSetor;
	}

	public void setResponsavelSetor(String responsavelSetor) {
		this.responsavelSetor = responsavelSetor;
	}

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

	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
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

	public String getTratamentoFormal() {
		return tratamentoFormal;
	}

	public void setTratamentoFormal(String tratamentoFormal) {
		this.tratamentoFormal = tratamentoFormal;
	}

	public String getEmailSetor() {
		return emailSetor;
	}

	public void setEmailSetor(String emailSetor) {
		this.emailSetor = emailSetor;
	}

	public String getGeneroDirigente() {
		return generoDirigente;
	}

	public void setGeneroDirigente(String generoDirigente) {
		this.generoDirigente = generoDirigente;
	}

	public String getGeneroSetor() {
		return generoSetor;
	}

	public void setGeneroSetor(String generoSetor) {
		this.generoSetor = generoSetor;
	}

	public String getCargoDirigente() {
		return cargoDirigente;
	}

	public void setCargoDirigente(String cargoDirigente) {
		this.cargoDirigente = cargoDirigente;
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

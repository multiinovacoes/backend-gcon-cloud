package br.com.multiinovacoes.gcon.model.request;

import java.time.LocalDate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class OrgaoRequest {
	
	@ApiModelProperty(value = "Descrição")
	private String descricao;
	
	@ApiModelProperty(value = "Status")
	private Integer status;
	
	@ApiModelProperty(value = "Sigla do Órgão")
	private String sigla;

	@ApiModelProperty(value = "Fone do Órgão")
	private String fone;

	@ApiModelProperty(value = "Nome Dirigente")
	private String nomeDirigente;

	@ApiModelProperty(value = "Email do Dirigente")
	private String emailDirigente;

	@ApiModelProperty(value = "Tratamento Formal")
	private String tratamentoFormal;

	@ApiModelProperty(value = "Email do Órgão")
	private String emailOrgao;

	@ApiModelProperty(value = "Gênero Dirigente do Órgão")
	private String generoDirigente;

	@ApiModelProperty(value = "Gênero do Órgão")
	private String generoOrgao;

	@ApiModelProperty(value = "Cargo Dirigente do Órgão")
	private String cargoDirigente;

	@ApiModelProperty(value = "Endereço do Órgão")
	private String endereco;

	@ApiModelProperty(value = "Complemento Endereço do Órgão")
	private String complemento;

	@ApiModelProperty(value = "Número Endereço do Órgão")
	private String numero;

	@ApiModelProperty(value = "Bairro Endereço do Órgão")
	private String bairro;

	@ApiModelProperty(value = "Cidade Endereço do Órgão")
	private String cidade;

	@ApiModelProperty(value = "UF Endereço do Órgão")
	private String uf;

	@ApiModelProperty(value = "CEP Endereço do Órgão")
	private String cep;

	@ApiModelProperty(value = "Data Criação do Órgão")
	private LocalDate dataCriacao;

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

	public String getEmailOrgao() {
		return emailOrgao;
	}

	public void setEmailOrgao(String emailOrgao) {
		this.emailOrgao = emailOrgao;
	}

	public String getGeneroDirigente() {
		return generoDirigente;
	}

	public void setGeneroDirigente(String generoDirigente) {
		this.generoDirigente = generoDirigente;
	}

	public String getGeneroOrgao() {
		return generoOrgao;
	}

	public void setGeneroOrgao(String generoOrgao) {
		this.generoOrgao = generoOrgao;
	}

	public String getCargoDirigente() {
		return cargoDirigente;
	}

	public void setCargoDirigente(String cargoDirigente) {
		this.cargoDirigente = cargoDirigente;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	

}

package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class UsuarioDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2975777060381851456L;

	@ApiModelProperty(value = "Id do usuário")
	private Long id;

	@ApiModelProperty(value = "nome do usuário")
	private String nome;
	
	@ApiModelProperty(value = "cpf do usuário")
	private String cpf;

	@ApiModelProperty(value = "Senha do usuário")
	private String senha;

	@ApiModelProperty(value = "órgão do usuário")
	private Long orgao;

	@ApiModelProperty(value = "Status do usuário")
	private String status;

	@ApiModelProperty(value = "Setor do usuário")
	private Long setor;
	
	@ApiModelProperty(value = "Logo")
	private String logo;

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getSetor() {
		return setor;
	}

	public void setSetor(Long setor) {
		this.setor = setor;
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

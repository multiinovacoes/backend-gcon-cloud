package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class LogoTipoDto {
	
	
	@ApiModelProperty(value = "Id do logotipo")
	private Long id;
	
	@ApiModelProperty(value = "órgão do logotipo")
	private Long orgao;
	
	@ApiModelProperty(value = "Nome do logotipo")
	private String nome;
	
	@ApiModelProperty(value = "Imagem do logotipo")
	private byte[] arquivo;
	
	@ApiModelProperty(value = "Status do logotipo")
	private Integer status;

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

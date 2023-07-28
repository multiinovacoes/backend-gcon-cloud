package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class ModeloDocumentoDto {
	

	@ApiModelProperty(value = "Id do modelo documento")
	private Long id;

	@ApiModelProperty(value = "Descrição do modelo documento")
	private String descricao;
	
	@ApiModelProperty(value = "Modelo do documento")
	private String modelo;

	@ApiModelProperty(value = "Tipo do modelo documento")
	private Integer tipo;
	
	@ApiModelProperty(value = "Status do modelo documento")
	private Integer status;
	
	@ApiModelProperty(value = "Órgão do modelo documento")
	private Long orgao;

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

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	

}

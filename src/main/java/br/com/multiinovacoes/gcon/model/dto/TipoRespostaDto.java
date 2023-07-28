package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class TipoRespostaDto {
	
	@ApiModelProperty(value = "Id do Tipo de Resposta")
	private Long id;

	@ApiModelProperty(value = "Orgão do Tipo de Resposta")
	private Long orgao;

	@ApiModelProperty(value = "Descrição")
	private String descricao;
	
	@ApiModelProperty(value = "Status")
	private Integer status;

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	

}

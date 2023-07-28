package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class DescricaoEmailDto {
	
	
	@ApiModelProperty(value = "Id da descrição email")
	private Long id;
	
	@ApiModelProperty(value = "Descrição da descrição email")
	private String descricao;
	
	@ApiModelProperty(value = "Órgão da descrição email")
	private Long orgao;
	
	@ApiModelProperty(value = "Status da descrição email")
	private Integer status;

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

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
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

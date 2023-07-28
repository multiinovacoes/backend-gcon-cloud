package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class AreaDto {
	
	@ApiModelProperty(value = "Id da área")	
	private Long id;

	@ApiModelProperty(value = "Id da área")	
	private Long idAntigo;

	@ApiModelProperty(value = "Descrição da área")
	private String descricao;
	
	@ApiModelProperty(value = "Status da área")
	private Integer status;
	
	@ApiModelProperty(value = "Órgão da área")
	private Long orgao;

	public Long getIdAntigo() {
		return idAntigo;
	}

	public void setIdAntigo(Long idAntigo) {
		this.idAntigo = idAntigo;
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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	
	


}

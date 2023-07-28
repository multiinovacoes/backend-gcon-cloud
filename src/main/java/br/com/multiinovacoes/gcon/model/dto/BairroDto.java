package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;


public class BairroDto {
	
	
	@ApiModelProperty(value = "Id do bairro")
	private Long id;
	
	@ApiModelProperty(value = "Id da rpa")
	private Long idRpa;
	
	@ApiModelProperty(value = "Id do orgão")
	private Long idOrgao;
	
	@ApiModelProperty(value = "Id da descrição")
	private String descricao;
	
	@ApiModelProperty(value = "Id do status")
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRpa() {
		return idRpa;
	}

	public void setIdRpa(Long idRpa) {
		this.idRpa = idRpa;
	}

	public Long getIdOrgao() {
		return idOrgao;
	}

	public void setIdOrgao(Long idOrgao) {
		this.idOrgao = idOrgao;
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

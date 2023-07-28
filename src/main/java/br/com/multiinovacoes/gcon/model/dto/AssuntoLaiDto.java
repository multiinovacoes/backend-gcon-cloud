package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;


public class AssuntoLaiDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;

	@ApiModelProperty(value = "Id do assunto")
	private Long id;

	@ApiModelProperty(value = "Id do órgão")
	private Long orgao;
	
	@ApiModelProperty(value = "Área do assunto")
	private Long area;

	@ApiModelProperty(value = "Descrição do assunto")
	private String descricao;
	
	@ApiModelProperty(value = "Status do assunto")
	private Integer status;
	
	
	
	public AssuntoLaiDto() {
	}
	
	
	public AssuntoLaiDto(Long id) {
		this.id = id;
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

	public Long getArea() {
		return area;
	}


	public void setArea(Long area) {
		this.area = area;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssuntoLaiDto other = (AssuntoLaiDto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

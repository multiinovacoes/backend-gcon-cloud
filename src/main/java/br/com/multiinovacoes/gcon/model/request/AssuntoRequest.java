package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AssuntoRequest {
	
	@ApiModelProperty(value = "Descrição do assunto")
	private String descricao;
	
	@ApiModelProperty(value = "Status do assunto")
	private Integer status;

	@ApiModelProperty(value = "Orgão do assunto")
	private Long orgao;

	@ApiModelProperty(value = "Área do assunto")
	private Long area;

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
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	

	

}

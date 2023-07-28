package br.com.multiinovacoes.gcon.model.request;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;


public class AreaLaiRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;


	@ApiModelProperty(value = "Id do órgão")
	private Long orgao;

	@ApiModelProperty(value = "Descrição da área")
	private String descricao;
	
	@ApiModelProperty(value = "Status da área")
	private Integer status;
	
	@ApiModelProperty(value = "Instituição da área")
	private Long instituicao;
	
	
	public AreaLaiRequest() {
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

	public Long getInstituicao() {
		return instituicao;
	}


	public void setInstituicao(Long instituicao) {
		this.instituicao = instituicao;
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

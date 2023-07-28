package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class ModeloDocumentoRequest {
	

	@ApiModelProperty(value = "Descrição do modelo documento")
	private String descricao;
	
	@ApiModelProperty(value = "Modelo do documento")
	private String modelo;

	@ApiModelProperty(value = "Tipo do modelo documento")
	private Integer tipo;
	
	@ApiModelProperty(value = "Status do modelo documento")
	private Integer status;
	
	@ApiModelProperty(value = "Orgão do modelo documento")
	private Long orgao;
	
	
	

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
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

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}


}

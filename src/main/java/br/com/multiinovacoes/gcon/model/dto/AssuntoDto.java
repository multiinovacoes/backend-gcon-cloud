package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;

import javax.persistence.Column;

import io.swagger.annotations.ApiModelProperty;

public class AssuntoDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5983126983252757484L;

	@ApiModelProperty(value = "Id do assunto")
	private Long id;

	@ApiModelProperty(value = "Descrição do assunto")
	private String descricao;
	
	@ApiModelProperty(value = "Status do assunto")
	private Integer status;

	@ApiModelProperty(value = "Orgão do assunto")
	private Long orgao;

	@ApiModelProperty(value = "Área do assunto")
	private Long area;
	
	@Column(name = "INCODIGOASSUNTO")
	private Long idAntigo;
	
	@Column(name = "INCODIGOAREAASSUNTO")
	private Long idAreaAntigo;
	

	public Long getIdAntigo() {
		return idAntigo;
	}

	public void setIdAntigo(Long idAntigo) {
		this.idAntigo = idAntigo;
	}

	public Long getIdAreaAntigo() {
		return idAreaAntigo;
	}

	public void setIdAreaAntigo(Long idAreaAntigo) {
		this.idAreaAntigo = idAreaAntigo;
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
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}

	

}

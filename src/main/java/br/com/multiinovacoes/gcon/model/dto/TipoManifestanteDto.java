package br.com.multiinovacoes.gcon.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class TipoManifestanteDto {
	
	@ApiModelProperty(value = "Id do tipo de manifestante")
	private Long id;

	@ApiModelProperty(value = "Descrição do tipo de manifestante")
	private String descricao;
	
	@ApiModelProperty(value = "Orgão do tipo de manifestante")
	private Long orgao;

	@ApiModelProperty(value = "Status do tipo de manifestante")
	private Integer status;
	
	@ApiModelProperty(value = "Id Antigo do tipo de manifestante")
	private Long idAntigo;

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

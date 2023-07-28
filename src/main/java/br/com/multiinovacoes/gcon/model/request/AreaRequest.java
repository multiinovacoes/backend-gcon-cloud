package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class AreaRequest {

    @ApiModelProperty(required = true, value = "Descrição da área")
	private String descricao;

    @ApiModelProperty(required = true, value = "Status da área")
	private Integer status;
    
    @ApiModelProperty(required = true, value = "Orgão da área")
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

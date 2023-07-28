/**
 * 
 */
package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author welss
 *
 */
@ApiModel
public class NaturezaRequest  {
	
	@ApiModelProperty(value = "Orgão da Natureza")
	private Long orgao;
	
    @ApiModelProperty(required = true, value = "Descrição da natureza")
	private String descricao;

    @ApiModelProperty(required = true, value = "Status da natureza")
	private Integer status;

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

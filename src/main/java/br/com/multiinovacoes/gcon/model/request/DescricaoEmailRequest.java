package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class DescricaoEmailRequest {

    @ApiModelProperty(required = true, value = "Id da descrição do email")
	private Long id;

    @ApiModelProperty(required = true, value = "Descrição da descrição do email")
	private String descricao;

    @ApiModelProperty(required = true, value = "Status da descrição do email")
	private Integer status;
    
    @ApiModelProperty(required = true, value = "Orgão da descrição do email")
    private Long orgao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

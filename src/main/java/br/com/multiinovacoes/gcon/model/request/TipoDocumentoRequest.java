package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@ApiModel
public class TipoDocumentoRequest {
	
	
	@ApiModelProperty(value = "Orgão do Tipo Documento")
	private Long orgao;

	@ApiModelProperty(value = "Descrição do Tipo Documento")
	private String descricao;

	@ApiModelProperty(value = "Mascara do Tipo Documento")
	private String mascara;

	@ApiModelProperty(value = "Status do Tipo Documento")
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

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
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

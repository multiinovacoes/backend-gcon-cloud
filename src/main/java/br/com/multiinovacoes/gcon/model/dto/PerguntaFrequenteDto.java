package br.com.multiinovacoes.gcon.model.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class PerguntaFrequenteDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5983126983252757484L;

	@ApiModelProperty(value = "Id da pergunta")
	private Long id;

	@ApiModelProperty(value = "Orgão da pergunta frequente")
	private Long orgao;

	@ApiModelProperty(value = "Pergunta frequente")
	private String pergunta;

	@ApiModelProperty(value = "Resposta da pergunta")
	private String resposta;

	@ApiModelProperty(value = "Status da pergunta")
	private Integer status;

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

	public String getPergunta() {
		return pergunta;
	}

	public void setPergunta(String pergunta) {
		this.pergunta = pergunta;
	}

	public String getResposta() {
		return resposta;
	}

	public void setResposta(String resposta) {
		this.resposta = resposta;
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

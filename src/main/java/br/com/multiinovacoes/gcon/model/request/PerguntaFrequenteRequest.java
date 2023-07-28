package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class PerguntaFrequenteRequest {
	
	@ApiModelProperty(value = "Descrição da pergunta")
	private String pergunta;
	
	@ApiModelProperty(value = "Resposta da pergunta")
	private String resposta;

	@ApiModelProperty(value = "Orgão da pergunta")
	private Long orgao;
	
	@ApiModelProperty(value = "Status da pergunta")
	private Integer status;

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

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
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

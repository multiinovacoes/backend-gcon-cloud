package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "OUVIDORIA_PERGUNTA_FREQUENTE")
public class PerguntaFrequente implements Serializable{
	
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 2990480504210054666L;

	@Id
	@Column(name = "CODIGO_PERGUNTA")
	private Long id;
	
	@Column(name = "CODIGO_ORGAO")
	private Long orgao;
	
	@Column(name = "PERGUNTA")
	private String pergunta;

	@Column(name = "RESPOSTA")
	private String resposta;

	@Column(name = "STATUS")
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
	
	


}

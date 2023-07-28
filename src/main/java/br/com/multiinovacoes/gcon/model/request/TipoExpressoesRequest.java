package br.com.multiinovacoes.gcon.model.request;

import io.swagger.annotations.ApiModelProperty;


public class TipoExpressoesRequest  {
	
	
	@ApiModelProperty(value = "Id do Tipo Expressão")	
	private Long id;

	@ApiModelProperty(value = "Nome da expressão")	
	private String expressao;

	@ApiModelProperty(value = "Campo do tipo de expressão")	
	private String campo;

	@ApiModelProperty(value = "Descrição da expressão")	
	private String descricao;

	@ApiModelProperty(value = "Numero de ordem da expressão")	
	private Integer ordem;

	@ApiModelProperty(value = "Objeto para substituir a expressão")	
	private String objetoAtendimento;
	
	@ApiModelProperty(value = "Orgão do tipo expressão")	
	private Long orgao;
	
	

	public Long getOrgao() {
		return orgao;
	}


	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getExpressao() {
		return expressao;
	}


	public void setExpressao(String expressao) {
		this.expressao = expressao;
	}


	public String getCampo() {
		return campo;
	}


	public void setCampo(String campo) {
		this.campo = campo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public Integer getOrdem() {
		return ordem;
	}


	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}


	public String getObjetoAtendimento() {
		return objetoAtendimento;
	}


	public void setObjetoAtendimento(String objetoAtendimento) {
		this.objetoAtendimento = objetoAtendimento;
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

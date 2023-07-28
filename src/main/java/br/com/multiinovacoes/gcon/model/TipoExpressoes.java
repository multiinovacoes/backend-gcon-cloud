package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "OUVIDORIA_TIPO_EXPRESSOES")
public class TipoExpressoes implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7104392469094602380L;

	@Id
	@Column(name = "IN_CODIGO_TIPO_EXPRESSAO")
	private Long id;

	@Column(name = "VA_EXPRESSAO")
	private String expressao;
	
	@Column(name = "VA_CAMPO")
	private String campo;
	
	@Column(name = "VA_DESCRICAO")
	private String descricao;
	
	@Column(name = "IN_ORDEM")
	private Integer ordem;
	
	@Column(name = "OBJETO")
	private String objetoAtendimento;
	
	@Column(name = "RELATORIO_GERAL")
	private Integer relatorioGeral;

	public Integer getRelatorioGeral() {
		return relatorioGeral;
	}


	public void setRelatorioGeral(Integer relatorioGeral) {
		this.relatorioGeral = relatorioGeral;
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
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoExpressoes other = (TipoExpressoes) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
	
	

}

package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "OUVIDORIA_SATISFACAO_PERGUNTAS")
public class PerguntaSatisfacao implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3766690804261829245L;

	@Id
	@Column(name = "IN_SATISFACAO_PERGUNTA_ID")
	private Long id;
	
	@Column(name = "IN_ORGAO")
	private Long orgao;
	
	@Column(name = "VA_DESCRICAO")
	private String descricao;
	
	@Column(name = "IN_ATIVO")
	private Integer status;
	
	@Transient
	private Long idResposta;
	
	public Long getIdResposta() {
		return idResposta;
	}

	public void setIdResposta(Long idResposta) {
		this.idResposta = idResposta;
	}

	@Transient
	private List<RespostaSatisfacao> listaRespostaSatisfacao;
	
	public List<RespostaSatisfacao> getListaRespostaSatisfacao() {
		return listaRespostaSatisfacao;
	}

	public void setListaRespostaSatisfacao(List<RespostaSatisfacao> listaRespostaSatisfacao) {
		this.listaRespostaSatisfacao = listaRespostaSatisfacao;
	}

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
		PerguntaSatisfacao other = (PerguntaSatisfacao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
}

package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "OUVIDORIA_DESCRICAO_OUVIDORIA")
public class DescricaoOuvidoria implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4253532154943994748L;
	
	@Id
	@Column(name = "IN_DESCRICAO_OUVIDORIA_ID")
	private Long id;
	
	@Column(name = "VA_DESCRICAO")
	private String descricao;
	
	@Column(name = "IN_ORGAO_ID")
	private Long orgao;
	
	@Column(name = "STATUS")
	private Integer status;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
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
		DescricaoOuvidoria other = (DescricaoOuvidoria) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

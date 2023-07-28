package br.com.multiinovacoes.gcon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "OUVIDORIA_BAIRRO_OCORRENCIA")
public class Bairro {
	
	
	
	@Id
	@Column(name = "IN_ID_BAIRRO_OCORRENCIA")
	private Long id;
	
	@Column(name = "IN_ID_RPA")
	private Long idRpa;
	
	@Column(name = "INCODIGOORGAO")
	private Long orgao;
	
	@Column(name = "VADESCRICAO")
	private String descricao;
	
	@Column(name = "IN_ATIVO")
	private Integer status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdRpa() {
		return idRpa;
	}

	public void setIdRpa(Long idRpa) {
		this.idRpa = idRpa;
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
		Bairro other = (Bairro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}

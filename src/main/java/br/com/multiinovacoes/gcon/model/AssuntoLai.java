package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "LAI_ASSUNTO")
public class AssuntoLai implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODIGO_ASSUNTO")
	private Long id;

	@Column(name = "CODIGO_ORGAO")
	private Long orgao;

	@Column(name = "CODIGO_AREA")
	private Long area;

	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "STATUS")
	private Integer status;
	
	
	public AssuntoLai() {
	}
	
	
	public AssuntoLai(Long id) {
		this.id = id;
	}

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

	public Long getArea() {
		return area;
	}


	public void setArea(Long area) {
		this.area = area;
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
		AssuntoLai other = (AssuntoLai) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "OUVIDORIA_ASSUNTO")
public class Assunto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "INCODIGOASSUNTONOVO")
	private Long id;

	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "VADESCRICAO")
	private String descricao;
	
	@Column(name = "SMATIVO")
	private Integer status;

	@NotNull(message = "Órgão é obrigatório")
	@Column(name = "INCODIGOORGAO")
	private Long orgao;

	@NotNull(message = "Área é obrigatório")
	@Column(name = "INCODIGOAREANOVO")
	private Long area;
	
	@Column(name = "INCODIGOASSUNTO")
	private Long idAntigo;
	
	@Column(name = "INCODIGOAREAASSUNTO")
	private Long idAreaAntigo;
	
	public Long getIdAntigo() {
		return idAntigo;
	}

	public void setIdAntigo(Long idAntigo) {
		this.idAntigo = idAntigo;
	}

	public Long getIdAreaAntigo() {
		return idAreaAntigo;
	}

	public void setIdAreaAntigo(Long idAreaAntigo) {
		this.idAreaAntigo = idAreaAntigo;
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
		Assunto other = (Assunto) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

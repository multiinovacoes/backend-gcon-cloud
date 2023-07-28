package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "OUVIDORIA_TIPO_MANIFESTANTE")
public class TipoManifestante implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "INTIPOMANIFESTANTENOVO")
	private Long id;
	
	@Column(name = "IN_TIPO_MANIFESTANTE_ID")
	private Long idAntigo;

	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "VA_DESCRICAO")
	private String descricao;
	
	@Column(name = "IN_ORGAO")
	private Long orgao;
	
	@Column(name = "STATUS")
	private Integer status;

	@Column(name = "SM_ATIVO")
	private Integer ativo;

	@Column(name = "IN_TIPO_DOCUMENTO")
	private Long tipoDocumento;
	
	@Column(name = "TIPO_DENUNCIA_SEXUAL")
	private Integer tipoDenunciaSexual;
	
	public Long getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(Long tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public Long getIdAntigo() {
		return idAntigo;
	}

	public void setIdAntigo(Long idAntigo) {
		this.idAntigo = idAntigo;
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

	public Integer getAtivo() {
		return ativo;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
	}

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}
	
	public Integer getTipoDenunciaSexual() {
		return tipoDenunciaSexual;
	}

	public void setTipoDenunciaSexual(Integer tipoDenunciaSexual) {
		this.tipoDenunciaSexual = tipoDenunciaSexual;
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
		TipoManifestante other = (TipoManifestante) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}

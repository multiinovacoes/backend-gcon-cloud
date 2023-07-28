package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;


@Entity
@Table(name = "OUVIDORIA_TIPO_DOCUMENTO")
public class TipoDocumento implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8610684747884519463L;

	
	@Id
	@Column(name = "INCODIGOTIPODOCUMENTONOVO")
	private Long id;
	
	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "VADESCRICAO")
	private String descricao;

	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "INCODIGOORGAO")
	private Long orgao;

	@Column(name = "VAMASCARA")
	private String mascara;
	
	@Column(name = "INCODIGOTIPODOCUMENTO")
	private Long idAntigo;

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

	public Long getOrgao() {
		return orgao;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public String getMascara() {
		return mascara;
	}

	public void setMascara(String mascara) {
		this.mascara = mascara;
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
		TipoDocumento other = (TipoDocumento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}

package br.com.multiinovacoes.gcon.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "OUVIDORIA_FERIADO")
public class Feriado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2426378029381142933L;
	
	@Id
	@Column(name = "INCODIGOFERIADO")
	private Long id;

	@NotBlank(message = "Descrição é obrigatório")
	@Column(name = "VAMOTIVO")
	private String descricao;
	
	@Column(name = "STATUS")
	private Integer status;
	
	@Column(name = "INCODIGOORGAO")
    private Long orgao;
	
	@Column(name = "DADATA")
	private LocalDate dataFeriado;

	@Column(name = "INCODIGOUSUARIO")
	private Long usuario;
		
	@Column(name = "DACRIACAO")
	private LocalDateTime dataCriacao;
	
	public LocalDate getDataFeriado() {
		return dataFeriado;
	}

	public void setDataFeriado(LocalDate dataFeriado) {
		this.dataFeriado = dataFeriado;
	}
	
	public Long getUsuario() {
		return usuario;
	}

	public void setUsuario(Long usuario) {
		this.usuario = usuario;
	}

	public void setOrgao(Long orgao) {
		this.orgao = orgao;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
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
		Feriado other = (Feriado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	

}
